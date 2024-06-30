package com.example.OnlineBlog.application;

import com.example.OnlineBlog.infrastructure.outputPort.IPermissionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionUseCase {

    @Autowired
    private IPermissionMethod permissionMethod;
}
