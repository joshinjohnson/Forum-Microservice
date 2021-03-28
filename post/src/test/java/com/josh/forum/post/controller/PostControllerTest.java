package com.josh.forum.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.forum.post.model.Post;
import com.josh.forum.post.model.User;
import com.josh.forum.post.model.UserPosts;
import com.josh.forum.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostControllerTest {

	@MockBean
	private PostService postService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void testGetAllPosts() throws Exception {
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(new User(1, "", "", ""));

		List<Post> mockPosts = new ArrayList<>();
		mockPosts.add(new Post(1, "Mocked content 1"));
		mockPosts.add(new Post(2, "Mocked content 2"));

		UserPosts userPosts = new UserPosts(mockUsers.get(0).getId(), mockPosts);

		when(postService.getAllPostsByUserId(mockUsers.get(0).getId())).thenReturn(userPosts);

		mockMvc.perform(
			MockMvcRequestBuilders.get("/1/posts/")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.posts", hasSize(2)))
			.andExpect(jsonPath("$.posts[0].contentText", is("Mocked content 1")));
	}

	@Test
	public void testAddPost() throws Exception {
		Post testPost = new Post(1, "Test post");
		List<Post> mockPosts = new ArrayList<>();
		mockPosts.add(new Post(1, "Mocked content 1"));
		mockPosts.add(testPost);

		UserPosts userPosts = new UserPosts(1, mockPosts);
		when(postService.addPostByUserId(1)).thenReturn(userPosts);

		mockMvc.perform(MockMvcRequestBuilders.post("/1/posts/")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(this.mapper.writeValueAsBytes(testPost)))
			.andExpect(status().isOk());
	}

}
