package com.example.OnlineBlog.infrastructure.dto;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String username) {
}
