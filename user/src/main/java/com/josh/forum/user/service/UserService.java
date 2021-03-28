package com.josh.forum.user.service;

import com.josh.forum.user.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	public boolean addUser(User user) {
		return false;
	}

	public List<User> getAllUsers() {
		return new ArrayList<>() {{
			add(new User(1, "Joshin", "Johnson", "joshinjohnson@gmail.com"));
		}};
	}
}
