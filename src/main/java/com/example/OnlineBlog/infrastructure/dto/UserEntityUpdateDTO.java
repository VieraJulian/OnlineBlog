package com.example.OnlineBlog.infrastructure.dto;

import lombok.Builder;

@Builder
public record UserEntityUpdateDTO(String username, String newUsername, String password, String newPassword) {
}
