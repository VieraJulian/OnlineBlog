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
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
