package com.ticketbooking.user_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ApiResponse<T> {
	private String message;
	private T data;
	 public ApiResponse() {
	    }

	    public ApiResponse(String message, T data) {
	        this.message = message;
	        this.data = data;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public T getData() {
	        return data;
	    }
	

}
