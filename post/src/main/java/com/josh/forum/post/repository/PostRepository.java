package com.josh.forum.post.repository;

import com.josh.forum.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, Long> {
    List<Post> findByUserId(long userId);
}
