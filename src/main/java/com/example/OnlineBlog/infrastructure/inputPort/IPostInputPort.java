package com.example.OnlineBlog.infrastructure.inputPort;

import com.example.OnlineBlog.domain.Post;

import java.util.List;
import java.util.Optional;

public interface IPostInputPort {

    Post save(Post post);
    Post update(Long id, Post post);
    Optional<Post> findById(Long id);
    List<Post> findAllPosts(int page, int size);
    String deleteById(Long id);
}
