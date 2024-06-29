package com.example.OnlineBlog.infrastructure.outputAdapter;

import com.example.OnlineBlog.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
