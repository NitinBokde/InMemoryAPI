package com.nitin.inmemoryapi.dto;

import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
	@NotBlank
	public String username;
	@NotBlank
	public String password;
}
