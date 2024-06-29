package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.Permission;
import com.example.OnlineBlog.infrastructure.outputPort.IPermissionMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PermissionRepositoryImp implements IPermissionMethod {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        return null;
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
