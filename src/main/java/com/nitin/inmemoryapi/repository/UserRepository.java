package com.nitin.inmemoryapi.repository;

import com.nitin.inmemoryapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
	private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

	public boolean existsByUsername(String username) {
		return users.containsKey(username);
	}

	public void save(User user) {
		users.put(user.getUsername(), user);
	}

	public User findByUsername(String username) {
		return users.get(username);
	}
}
