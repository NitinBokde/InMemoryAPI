package com.nitin.inmemoryapi.service;

import com.nitin.inmemoryapi.config.JwtUtil;
import com.nitin.inmemoryapi.model.User;
import com.nitin.inmemoryapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	private final UserRepository userRepo;
	private final JwtUtil jwtUtil;

	public AuthService(UserRepository userRepo, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.jwtUtil = jwtUtil;
	}

	public void register(String username, String password) {
		if (userRepo.existsByUsername(username)) {
			throw new RuntimeException("Username already taken");
		}
		userRepo.save(new User(username, password));
		logger.info("Registered new user: {}", username);
	}

	public String login(String username, String password) {
		User u = userRepo.findByUsername(username);
		if (u == null || !u.getPassword().equals(password)) {
			throw new RuntimeException("Invalid credentials");
		}
		logger.info("User logged in: {}", username);
		return jwtUtil.generateToken(username);
	}
}
