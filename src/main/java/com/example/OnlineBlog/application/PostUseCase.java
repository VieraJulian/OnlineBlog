package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.domain.UserEntity;
import com.example.OnlineBlog.infrastructure.dto.PostDTO;
import com.example.OnlineBlog.infrastructure.dto.UserDTO;
import com.example.OnlineBlog.infrastructure.inputPort.IPostInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IPostMethod;
import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostUseCase implements IPostInputPort {

    @Autowired
    private IPostMethod postMethod;

    @Autowired
    private IUserMethod userMethod;

    @Override
    public PostDTO save(Post post) {
        UserEntity user = userMethod.findById(post.getUser().getId()).orElse(null);

        if (user != null) {
            post.setUser(user);
            Post postNew = postMethod.save(post);

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .build();

            return PostDTO.builder()
                    .id(postNew.getId())
                    .title(postNew.getTitle())
                    .description(postNew.getDescription())
                    .enabled(postNew.isEnabled())
                    .user(userDTO)
                    .build();
        }

        return null;
    }

    @Override
    public PostDTO update(Long id, Post post) {
        Optional<Post> postDB = postMethod.findById(id);

        if (postDB.isPresent()) {
            Post postU = postDB.get();
            postU.setTitle(post.getTitle());
            postU.setDescription(post.getDescription());

            Post postUpdated = postMethod.save(postU);

            UserDTO userDTO = UserDTO.builder()
                    .id(postUpdated.getUser().getId())
                    .username(postUpdated.getUser().getUsername())
                    .build();

            return PostDTO.builder()
                    .id(postUpdated.getId())
                    .title(postUpdated.getTitle())
                    .description(postUpdated.getDescription())
                    .user(userDTO)
                    .build();
        }

        return null;
    }

    @Override
    public Optional<PostDTO> findById(Long id) {
        Post post = postMethod.findById(id).orElseGet(null);

        UserDTO user = UserDTO.builder()
                    .id(post.getUser().getId())
                    .username(post.getUser().getUsername())
                    .build();

        return Optional.ofNullable(PostDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .enabled(post.isEnabled())
                    .user(user)
                    .build());

    }

    @Override
    public List<PostDTO> findAllPosts(int page, int size) {
        List<Post> postList = postMethod.findAll(page, size);
        List<PostDTO> postDTOList = new ArrayList<>();

        postList.forEach(p -> {
            UserDTO user = UserDTO.builder()
                   .id(p.getUser().getId())
                   .username(p.getUser().getUsername())
                   .build();

            postDTOList.add(PostDTO.builder()
                   .id(p.getId())
                   .title(p.getTitle())
                   .description(p.getDescription())
                   .enabled(p.isEnabled())
                   .user(user)
                   .build());
        });

        return postDTOList;

    }

    @Override
    public String deleteById(Long id) {
        postMethod.deleteById(id);
        return "Post deleted successfully.";
    }
}
