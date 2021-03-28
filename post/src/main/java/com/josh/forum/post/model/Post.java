package com.josh.forum.post.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@Document(collection = "post")
public class Post {

	@Id
	@NonNull
	private long id;

	@NonNull
	private String contentText;

	public Post(long id, String contentText) {
		this.id = id;
		this.contentText = contentText;
	}
}
