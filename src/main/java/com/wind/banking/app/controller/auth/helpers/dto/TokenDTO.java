package com.wind.banking.app.controller.auth.helpers.dto;


public class TokenDTO {
	
	public String expValue = "3600";
	public String type = "Bearer";
	public String token;
	
	public TokenDTO(String token) {
		this.token = token;
	}

}
