package com.nitin.inmemoryapi.controller;

import com.nitin.inmemoryapi.dto.*;
import com.nitin.inmemoryapi.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Validated @RequestBody SignupRequest req) {
		authService.register(req.username, req.password);
		return ResponseEntity.ok("User registered");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginRequest req) {
		String token = authService.login(req.username, req.password);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
