package com.example.OnlineBlog.infrastructure.inputPort;

import com.example.OnlineBlog.domain.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionInputPort {

    Permission save(Permission permission);
    Permission update(Long id, Permission permission);
    Optional<Permission> findById(Long id);
    List<Permission> permissionsList();
    String deleteById(Long id);

}