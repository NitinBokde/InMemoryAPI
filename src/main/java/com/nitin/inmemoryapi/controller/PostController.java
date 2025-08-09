package com.nitin.inmemoryapi.controller;

import com.nitin.inmemoryapi.dto.PostRequest;
import com.nitin.inmemoryapi.model.Post;
import com.nitin.inmemoryapi.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<?> createPost(@Validated @RequestBody PostRequest req, Authentication auth) {
		String username = auth.getName();
		Post p = postService.create(username, req.content);
		return ResponseEntity.ok(p);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id, Authentication auth) {
		postService.delete(auth.getName(), id);
		return ResponseEntity.ok("Deleted");
	}

	@PostMapping("/{id}/like")
	public ResponseEntity<?> likePost(@PathVariable Long id, Authentication auth) {
		postService.like(auth.getName(), id);
		return ResponseEntity.ok("Liked");
	}

	@GetMapping
	public ResponseEntity<?> listPosts() {
		List<Post> all = postService.listAll();
		return ResponseEntity.ok(all);
	}
}
