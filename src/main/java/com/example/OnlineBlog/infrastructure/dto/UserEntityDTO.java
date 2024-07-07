package com.example.OnlineBlog.infrastructure.dto;

import com.example.OnlineBlog.domain.Role;
import lombok.Builder;

@Builder
public record UserEntityDTO(Long id, String username, boolean enabled, boolean accountNotExpired, boolean accountNotLocked, boolean credentialNotExpired, Role role) {
}
