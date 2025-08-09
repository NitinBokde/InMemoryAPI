package com.nitin.inmemoryapi.repository;

import com.nitin.inmemoryapi.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
	private ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
	private AtomicLong idCounter = new AtomicLong(1);

	public Post save(Post post) {
		if (post.getId() == null) {
			post = new Post(idCounter.getAndIncrement(), post.getOwnerUsername(), post.getContent());
		}
		posts.put(post.getId(), post);
		return post;
	}

	public Optional<Post> findById(Long id) {
		return Optional.ofNullable(posts.get(id));
	}

	public void delete(Long id) {
		posts.remove(id);
	}

	public List<Post> findAll() {
		return new ArrayList<>(posts.values());
	}
}
