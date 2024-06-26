package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
