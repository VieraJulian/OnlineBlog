package com.example.OnlineBlog.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequestDTO(@NotBlank String username, @NotBlank String password) {
}
