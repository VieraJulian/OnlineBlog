package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.infrastructure.inputPort.IPostInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IPostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostUseCase implements IPostInputPort {

    @Autowired
    private IPostMethod postMethod;

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post update(Long id, Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAllPosts(int page, int size) {
        return List.of();
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }
}
