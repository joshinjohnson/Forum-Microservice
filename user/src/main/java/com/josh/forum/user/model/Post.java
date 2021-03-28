package com.josh.forum.user.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;

public class Post {

	@Id
	@NonNull
	private long id;

	@NonNull
	private String contentText;
}
