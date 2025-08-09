package com.nitin.inmemoryapi.model;

import java.util.*;

public class User {
	private String username;
	private String password;
	private Set<Long> likedPostIds = new HashSet<>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<Long> getLikedPostIds() {
		return likedPostIds;
	}
}
