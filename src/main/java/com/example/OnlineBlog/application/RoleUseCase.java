package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Permission;
import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.infrastructure.inputPort.IRoleInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IPermissionMethod;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleUseCase implements IRoleInputPort {

    @Autowired
    private IRoleMethod roleMethod;

    @Autowired
    private IPermissionMethod permissionMethod;

    @Override
    public Role save(Role role) {
        Set<Permission> permissionsList = new HashSet<>();
        Permission permissionRead;

        for (Permission permission : role.getPermissions()) {
            permissionRead = permissionMethod.findById(permission.getId()).orElse(null);

            if (permissionRead != null) {
                permissionsList.add(permissionRead);
            }
        }

        role.setPermissions(permissionsList);
        return roleMethod.save(role);
    }

    @Override
    public Role update(Long id, Role role) {
        Optional<Role> roleDB = roleMethod.findById(id);
        Set<Permission> permissionsList = new HashSet<>();
        Permission permissionRead;

        for (Permission permission : role.getPermissions()) {
            permissionRead = permissionMethod.findById(permission.getId()).orElse(null);

            if (permissionRead != null) {
                permissionsList.add(permissionRead);
            }
        }

        if (!permissionsList.isEmpty()){
            Role roleU = roleDB.get();
            roleU.setPermissions(permissionsList);
            return roleMethod.save(roleU);
        }

        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleMethod.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleMethod.findAll();
    }

    @Override
    public String deleteById(Long id) {
        roleMethod.deleteById(id);
        return "Role deleted successfully.";
    }
}
