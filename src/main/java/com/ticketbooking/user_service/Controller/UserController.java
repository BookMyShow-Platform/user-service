package com.ticketbooking.user_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.user_service.DTO.ApiResponse;
import com.ticketbooking.user_service.DTO.LoginRequestDto;
import com.ticketbooking.user_service.DTO.LoginResponseDto;
import com.ticketbooking.user_service.DTO.RegisterRequestDto;
import com.ticketbooking.user_service.DTO.RegisterResponseDto;
import com.ticketbooking.user_service.Service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private UserService service;
	public UserController(UserService service) {
		this.service = service; 
	}
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<RegisterResponseDto>> register(@RequestBody RegisterRequestDto registerRequest) {
		RegisterResponseDto res= service.register(registerRequest);
		return ResponseEntity.ok(new ApiResponse<RegisterResponseDto>("User Registerd successfully", res));
		
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponseDto>> login (@RequestBody LoginRequestDto request) {
		LoginResponseDto response = service.login(request);
		return ResponseEntity.ok(new ApiResponse<LoginResponseDto>("LoggedIn SuccessFully", response));
	}
	@GetMapping("/test")
	public ResponseEntity<ApiResponse<String>> test () {
		
		return ResponseEntity.ok(new ApiResponse<String>("LoggedIn SuccessFully", null));
	}


}
