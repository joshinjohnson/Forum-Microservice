package com.josh.forum.post.model;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "post")
public class Post {

	@NonNull
	private long userId;

	@NonNull
	private String contentText;

	public Post(long userId, String contentText) {
		this.userId = userId;
		this.contentText = contentText;
	}

}
