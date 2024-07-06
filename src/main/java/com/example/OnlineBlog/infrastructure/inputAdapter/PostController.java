package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.infrastructure.dto.PostDTO;
import com.example.OnlineBlog.infrastructure.inputPort.IPostInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostInputPort postInputPort;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        try {
            Optional<PostDTO> post = postInputPort.findById(id);
            return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody Post post) {
        try {
            PostDTO postNew = postInputPort.save(post);
            return new ResponseEntity<>(postNew, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody Post post) {
        try {
            PostDTO postUpdated = postInputPort.update(id, post);
            return new ResponseEntity<>(postUpdated, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPost(@RequestParam int page, @RequestParam int size){
        try {
            List<PostDTO> postList = postInputPort.findAllPosts(page, size);
            return new ResponseEntity<>(postList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            String msg = postInputPort.deleteById(id);
            return new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
