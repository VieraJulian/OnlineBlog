package com.example.OnlineBlog.infrastructure.inputAdapter;

import com.example.OnlineBlog.infrastructure.inputPort.IRoleInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private IRoleInputPort roleInputPort;
}
