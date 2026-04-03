package com.book_system.book_service.controller.response;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

public record BookResponseDto (
        UUID id,
        String title,
        UUID authorId,
        String isbn,
        String publisher,
        Date publicationDate,
        String edition,
        String genreId,
        Integer pageCount,
        String description,
        String coverUrl,
        Instant createdAt,
        Instant updatedAt
) {
}
