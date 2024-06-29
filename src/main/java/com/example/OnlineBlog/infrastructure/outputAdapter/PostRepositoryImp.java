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
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
