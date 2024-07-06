package com.example.OnlineBlog.infrastructure.inputPort;

import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.infrastructure.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostInputPort {

    PostDTO save(Post post);
    PostDTO update(Long id, Post post);
    Optional<PostDTO> findById(Long id);
    List<PostDTO> findAllPosts(int page, int size);
    String deleteById(Long id);
}
