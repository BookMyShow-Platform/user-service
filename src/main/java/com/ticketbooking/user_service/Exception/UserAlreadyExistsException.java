package com.ticketbooking.user_service.Exception;

public class UserAlreadyExistsException extends RuntimeException{
	public UserAlreadyExistsException(String message) {
		super(message);
	}
} 
