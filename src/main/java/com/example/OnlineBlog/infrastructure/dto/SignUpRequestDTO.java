package com.example.OnlineBlog.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDTO(@NotBlank String username, @NotBlank String password, @NotBlank String email) {
}
