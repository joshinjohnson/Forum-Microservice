package com.josh.forum.post.controller;

import com.josh.forum.post.model.Post;
import com.josh.forum.post.model.UserPosts;
import com.josh.forum.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/{userId}/post")
	public UserPosts getAllPosts(@PathVariable long userId) {
		return postService.getAllPostsByUserId(userId);
	}

	@PostMapping("/{userId}/post/")
	public ResponseEntity<Map<String, String>> addPost(@PathVariable long userId, @RequestBody Post post) {
		Map<String, String> response = new HashMap<>();

		if(postService.addPost(post)) {
			response.put("message", "success");
			return ResponseEntity.accepted().body(response);
		}

		response.put("message", "error");
		return ResponseEntity.badRequest().body(response);
	}
}
