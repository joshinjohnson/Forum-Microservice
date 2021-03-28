package com.josh.forum.post.controller;

import com.josh.forum.post.model.UserPosts;
import com.josh.forum.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/{userId}/posts")
	public UserPosts getAllPosts(@PathVariable long userId) {
		return postService.getAllPostsByUserId(userId);
	}

	@PostMapping("/{userId}/posts/")
	public UserPosts addPost(@PathVariable long userId) {
		return postService.addPostByUserId(userId);
	}
}
