package com.ticketbooking.user_service.DTO;

public class LoginResponseDto {
	private String accessToken;

	public LoginResponseDto() {
		super();
	}

	public LoginResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	

}
