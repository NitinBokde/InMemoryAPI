package com.nitin.inmemoryapi.dto;

import jakarta.validation.constraints.NotBlank;

public class PostRequest {
	@NotBlank
	public String content;
}
