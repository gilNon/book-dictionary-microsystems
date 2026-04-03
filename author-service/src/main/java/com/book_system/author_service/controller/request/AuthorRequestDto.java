package com.book_system.author_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorRequestDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotNull(message = "Birth date is required")
        LocalDate birthDate,
        @NotNull(message = "Nationality ID is required")
        UUID nationalityId,
        @NotBlank @Pattern(
                regexp = "^(http|https)://.*$",
                message = "Valid URL is required")
        String photoUrl
) {}
