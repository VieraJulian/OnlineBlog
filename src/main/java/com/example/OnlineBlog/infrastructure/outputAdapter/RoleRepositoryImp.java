package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleRepositoryImp implements IRoleMethod {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
