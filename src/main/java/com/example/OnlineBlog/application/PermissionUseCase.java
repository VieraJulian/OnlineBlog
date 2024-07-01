package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Permission;
import com.example.OnlineBlog.domain.Post;
import com.example.OnlineBlog.infrastructure.inputPort.IPermissionInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IPermissionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionUseCase implements IPermissionInputPort {

    @Autowired
    private IPermissionMethod permissionMethod;

    @Override
    public Permission save(Permission permission) {
        return permissionMethod.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        Optional<Permission> permissionDB = permissionMethod.findById(id);

        permissionDB.ifPresent(perm -> perm.setPermissionName(permission.getPermissionName()));

        return null;
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionMethod.findById(id);
    }

    @Override
    public List<Permission> permissionsList() {
        return permissionMethod.findAll();
    }

    @Override
    public String deleteById(Long id) {
        permissionMethod.deleteById(id);
        return "Permission deleted successfully.";
    }
}
