package com.example.OnlineBlog.infrastructure.outputPort;

import com.example.OnlineBlog.domain.Permission;

import java.util.Optional;

public interface IPermissionMethod {

    Permission save(Permission permission);
    Optional<Permission> findById(Long id);
    void deleteById(Long id);
}
