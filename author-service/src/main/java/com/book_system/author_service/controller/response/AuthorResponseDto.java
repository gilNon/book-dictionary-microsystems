package com.book_system.author_service.controller.response;

import java.time.Instant;
import java.util.UUID;

public record AuthorResponseDto(
        UUID id,
        String name,
        String lastName,
        String birthDate,
        String nationality,
        String photoUrl,
        Instant createdAt,
        Instant updatedAt
) {}

