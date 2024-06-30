package com.example.OnlineBlog.application;

import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUseCase {

    @Autowired
    private IRoleMethod roleMethod;
}
