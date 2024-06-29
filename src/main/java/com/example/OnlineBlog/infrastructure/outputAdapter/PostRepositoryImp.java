package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.infrastructure.outputPort.IPostMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostRepositoryImp implements IPostMethod {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
