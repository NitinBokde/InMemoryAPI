package com.nitin.inmemoryapi.config;

import com.nitin.inmemoryapi.model.User; // your domain user
import com.nitin.inmemoryapi.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepo) {
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		String header = req.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			try {
				if (jwtUtil.validateToken(token)) {
					String username = jwtUtil.getUsernameFromToken(token);

					// load your domain user
					User domainUser = userRepo.findByUsername(username);
					if (domainUser != null) {
						// build Spring Security UserDetails
						UserDetails userDetails = org.springframework.security.core.userdetails.User
								.withUsername(username).password(domainUser.getPassword())
								.authorities(Collections.emptyList()).build();

						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
								null, userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (JwtException e) {
				logger.error("JWT processing failed: {}", e.getMessage());
			}
		}

		// continue filter chain
		chain.doFilter(req, res);
	}
}
