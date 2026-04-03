package com.book_system.book_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;
import java.util.UUID;

public record BookRequestDto(
        @NotBlank(message = "Title is required")
        String title,
        @NotNull(message = "Author ID is required")
        UUID authorId,
        @NotBlank(message = "ISBN is required")
        String isbn,
        @NotBlank(message = "Publisher is required")
        String publisher,
        @NotNull(message = "Publication date is required")
        Date publicationDate,
        @NotBlank(message = "Edition is required")
        String edition,
        @NotNull(message = "Genre ID is required")
        UUID genreId,
        @NotNull(message = "Page count is required")
        Integer pageCount,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Cover URL is required")
        String coverUrl
) {
}
