package com.josh.forum.user.controller;

import com.josh.forum.user.model.User;
import com.josh.forum.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
		Map<String, String> response = new HashMap<>();

		boolean isSuccess = userService.addUser(user);
		if (isSuccess) {
			response.put("message", "success");
			return ResponseEntity.accepted().body(response);
		}

		response.put("message", "error");
		return ResponseEntity.badRequest().body(response);
	}

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
