package com.nitin.inmemoryapi.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Post {
	private Long id;
	private String ownerUsername;
	private String content;
	private Instant createdAt;
	private Set<String> likedBy = new HashSet<>();

	public Post(Long id, String ownerUsername, String content) {
		this.id = id;
		this.ownerUsername = ownerUsername;
		this.content = content;
		this.createdAt = Instant.now();
	}

	// getters setters
	public Long getId() {
		return id;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public String getContent() {
		return content;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Set<String> getLikedBy() {
		return likedBy;
	}
}
