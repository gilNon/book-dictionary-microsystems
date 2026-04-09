package com.book_system.book_service.controller.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDto (
        UUID id,
        String title,
        UUID authorId,
        String isbn,
        String publisher,
        LocalDate publicationDate,
        String edition,
        String genreId,
        Integer pageCount,
        String description,
        String coverUrl,
        Instant createdAt,
        Instant updatedAt
) {
}
