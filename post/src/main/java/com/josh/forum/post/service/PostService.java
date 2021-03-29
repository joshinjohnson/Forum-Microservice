package com.josh.forum.post.service;

import com.josh.forum.post.model.Post;
import com.josh.forum.post.model.UserPosts;
import com.josh.forum.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public UserPosts getAllPostsByUserId(long userId) {
		return new UserPosts(userId, postRepository.findByUserId(userId));
	}

	public boolean addPost(Post post) {
		return postRepository.save(post) != null ? true : false;
	}
}
