package com.josh.forum.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.forum.user.model.User;
import com.josh.forum.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void testAddUser() throws Exception {
		when(userService.addUser(any(User.class))).thenReturn(true);

		User user = new User("Joshin", "Johnson", "joshinjohnson@gmail.com");

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/register")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(this.mapper.writeValueAsBytes(user));

		mockMvc.perform(builder)
			.andExpect(status().isAccepted());
	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(new User("Joshin", "Johnson", "john@gmail.com"));
		mockUsers.add(new User("Zoshin", "Zohnson", "zohn@gmail.com"));
		when(userService.getAllUsers()).thenReturn(mockUsers);

		mockMvc.perform(
			MockMvcRequestBuilders.get("/user/")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].firstName", is("Joshin")));
	}

}
