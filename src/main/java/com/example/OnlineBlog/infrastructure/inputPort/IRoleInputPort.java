package com.example.OnlineBlog.infrastructure.inputPort;
import com.example.OnlineBlog.domain.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleInputPort {

    Role save(Role role);
    Role update(Long id, Role role);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    String deleteById(Long id);
}
