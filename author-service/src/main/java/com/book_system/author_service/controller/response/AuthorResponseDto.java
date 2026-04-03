package com.book_system.author_service.controller.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDto(
        UUID id,
        String name,
        String lastName,
        LocalDate birthDate,
        String nationality,
        String photoUrl,
        Instant createdAt,
        Instant updatedAt
) {}

