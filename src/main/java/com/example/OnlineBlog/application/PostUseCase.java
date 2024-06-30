package com.example.OnlineBlog.application;

import com.example.OnlineBlog.infrastructure.outputPort.IPostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostUseCase {

    @Autowired
    private IPostMethod postMethod;
}
