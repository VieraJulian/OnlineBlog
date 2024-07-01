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
        return postMethod.save(post);
    }

    @Override
    public Post update(Long id, Post post) {
        Optional<Post> postDB = postMethod.findById(id);

        if (postDB.isPresent()) {
            Post postU = postDB.get();
            postU.setTitle(post.getTitle());
            postU.setDescription(post.getDescription());
            return postMethod.save(postU);
        }

        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postMethod.findById(id);
    }

    @Override
    public List<Post> findAllPosts(int page, int size) {
        return postMethod.findAll(page, size);
    }

    @Override
    public String deleteById(Long id) {
        postMethod.deleteById(id);
        return "Post deleted successfully.";
    }
}
