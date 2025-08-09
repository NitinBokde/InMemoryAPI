package com.nitin.inmemoryapi.service;

import com.nitin.inmemoryapi.model.Post;
import com.nitin.inmemoryapi.model.User;
import com.nitin.inmemoryapi.repository.PostRepository;
import com.nitin.inmemoryapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	private final PostRepository postRepo;
	private final UserRepository userRepo;

	public PostService(PostRepository postRepo, UserRepository userRepo) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}

	public Post create(String username, String content) {
		Post p = new Post(null, username, content);
		Post saved = postRepo.save(p);
		logger.info("Post created: {} by {}", saved.getId(), username);
		return saved;
	}

	public void delete(String username, Long postId) {
		Post p = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
		if (!p.getOwnerUsername().equals(username)) {
			throw new RuntimeException("Not owner");
		}
		postRepo.delete(postId);
		logger.info("Post deleted: {} by {}", postId, username);
	}

	public void like(String username, Long postId) {
		Post p = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
		User u = userRepo.findByUsername(username);
		if (p.getLikedBy().contains(username)) {
			throw new RuntimeException("Already liked");
		}
		p.getLikedBy().add(username);
		u.getLikedPostIds().add(postId);
		logger.info("User {} liked post {}", username, postId);
	}

	public List<Post> listAll() {
		return postRepo.findAll();
	}
}
