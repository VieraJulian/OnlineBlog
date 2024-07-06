package com.example.OnlineBlog.infrastructure.dto;

import lombok.Builder;

@Builder
public record PostDTO (Long id, String title, String description, boolean enabled, UserDTO user){
}
