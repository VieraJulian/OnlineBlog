package com.example.OnlineBlog.application;

import com.example.OnlineBlog.infrastructure.outputPort.IUserMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase {

    @Autowired
    private IUserMethod userMethod;
}
