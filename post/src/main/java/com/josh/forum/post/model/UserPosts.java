package com.josh.forum.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserPosts {
	private long userId;
	private List<Post> posts;

	public UserPosts(long userId, List<Post> posts) {
		this.userId = userId;
		this.posts = posts;
	}
}
