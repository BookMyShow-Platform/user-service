package com.ticketbooking.user_service.Entity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticketbooking.user_service.Exception.UserNotFoundException;
import com.ticketbooking.user_service.Repository.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService {
	private UserRepo userRepo;
	public MyUserDetailsService (UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
		
		return new UserPrincipal(user);
	}

}
