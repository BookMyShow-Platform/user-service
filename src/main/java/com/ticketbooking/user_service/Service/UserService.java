package com.ticketbooking.user_service.Service;


import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticketbooking.user_service.Config.JwtConfig;
import com.ticketbooking.user_service.DTO.LoginRequestDto;
import com.ticketbooking.user_service.DTO.LoginResponseDto;
import com.ticketbooking.user_service.DTO.RegisterRequestDto;
import com.ticketbooking.user_service.DTO.RegisterResponseDto;
import com.ticketbooking.user_service.Entity.Role;
import com.ticketbooking.user_service.Entity.User;
import com.ticketbooking.user_service.Entity.UserPrincipal;
import com.ticketbooking.user_service.Exception.RoleNotFoundException;
import com.ticketbooking.user_service.Exception.UserAlreadyExistsException;
import com.ticketbooking.user_service.Exception.UserNotFoundException;
import com.ticketbooking.user_service.Repository.RoleRepo;
import com.ticketbooking.user_service.Repository.UserRepo;
@Service
public class UserService {
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	private AuthenticationManager authManager;
	
	public UserService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authManager) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService  = jwtService;
		this.authManager = authManager;
	}
	public RegisterResponseDto register(RegisterRequestDto registerRequest) {
		if (userRepo.existsByEmail(registerRequest.getEmail())) 
		{ 
			throw new UserAlreadyExistsException( "Email already registered"); 
			}
		User user = new User();
		user.setName(registerRequest.getName());
		String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
		user.setPassword(encodedPassword);
		Role role = roleRepo.findByName(registerRequest.getRole()).orElseThrow(() -> new RoleNotFoundException("Role not found"));;
		user.setRole(role);
		user.setEmail(registerRequest.getEmail());
		userRepo.save(user);
		RegisterResponseDto response = new RegisterResponseDto();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole().getName());
		return response;	
	}
	public LoginResponseDto login(LoginRequestDto request) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//		if (authentication.isAuthenticated()) {
//			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//			String role = authorities.stream().findFirst().map(GrantedAuthority::getAuthority).orElseThrow(()->new RuntimeException("Role not found"));
		    UserPrincipal principal =
		            (UserPrincipal) authentication.getPrincipal();

		    long userId = principal.getUserId();
		    String email = principal.getUsername();
		    String role = principal.getRole();

		    String token = jwtService.generateAccessToken(
		            userId,
		            email,
		            role
		    );
			
			LoginResponseDto response = new LoginResponseDto();
			response.setAccessToken(token);
			return response;
			
//		}
	}
//	public LoginResponseDto login(LoginRequestDto request) {
//		User user = userRepo.findByEmail(request.getEmail()).orElseThrow(()->new UserNotFoundException("User Not Found. Enter Correct Email"));
//		
//		boolean passwordMatches = passwordEncoder.matches(request.getPassword(),user.getPassword());
//		if (!passwordMatches) {
//			throw new RuntimeException("Invalid username or password");
//		}
//		String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().getName());
//		LoginResponseDto response= new LoginResponseDto();
//		response.setAccessToken(token);
//		return response;
//		
//	}

}
