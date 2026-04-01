package com.book_system.author_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthorRequestDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Birth date is required")
        String birthDate,
        @NotNull(message = "Nationality ID is required")
        UUID nationalityId,
        @NotBlank(message = "Photo URL is required")
        String photoUrl
) {}
