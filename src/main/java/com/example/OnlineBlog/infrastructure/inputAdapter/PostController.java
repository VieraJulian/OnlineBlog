package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.infrastructure.inputPort.IPostInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private IPostInputPort postInputPort;
}
