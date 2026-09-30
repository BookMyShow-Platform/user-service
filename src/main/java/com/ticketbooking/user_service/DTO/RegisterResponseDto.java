package com.ticketbooking.user_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RegisterResponseDto {
	private String name;
	private String email;
	private String role;
	public RegisterResponseDto() {
		super();
	}
	public RegisterResponseDto(String name, String email, String role) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
