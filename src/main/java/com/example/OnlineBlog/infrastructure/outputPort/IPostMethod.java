package com.example.OnlineBlog.infrastructure.outputPort;

import com.example.OnlineBlog.domain.Post;

import java.util.List;
import java.util.Optional;

public interface IPostMethod {

    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll(int page, int size);
    void deleteById(Long id);
}
