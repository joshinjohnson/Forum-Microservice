package com.josh.forum.user.service;

import com.josh.forum.user.model.User;
import com.josh.forum.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean addUser(User user) {
		return userRepository.save(user) != null ? true : false;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
