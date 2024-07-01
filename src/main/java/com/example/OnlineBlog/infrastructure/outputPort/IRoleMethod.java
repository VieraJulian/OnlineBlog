package com.example.OnlineBlog.infrastructure.outputPort;

import com.example.OnlineBlog.domain.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleMethod {

    Role save(Role role);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    void deleteById(Long id);
}
