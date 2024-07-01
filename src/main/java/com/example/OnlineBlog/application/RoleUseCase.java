package com.example.OnlineBlog.application;

import com.example.OnlineBlog.domain.Role;
import com.example.OnlineBlog.infrastructure.inputPort.IRoleInputPort;
import com.example.OnlineBlog.infrastructure.outputPort.IRoleMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleUseCase implements IRoleInputPort {

    @Autowired
    private IRoleMethod roleMethod;

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role update(Long id, Role role) {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }
}
