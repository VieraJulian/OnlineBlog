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
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
}
