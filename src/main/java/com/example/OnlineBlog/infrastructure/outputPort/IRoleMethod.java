package com.example.OnlineBlog.infrastructure.outputPort;

import com.example.OnlineBlog.domain.Role;

import java.util.Optional;

public interface IRoleMethod {

    Role save(Role role);
    Optional<Role> findById(Long id);
    void deleteById(Long id);
}
