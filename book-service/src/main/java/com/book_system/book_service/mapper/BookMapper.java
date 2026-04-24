package com.book_system.book_service.mapper;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.entity.BookEntity;

import java.time.LocalDate;

public final class BookMapper {

    private BookMapper() {
    }


    public static BookEntity toEntity(BookRequestDto request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setActive(true);
        bookEntity.setDescription(request.description());
        bookEntity.setIsbn(request.isbn());
        bookEntity.setPageCount(request.pageCount());
        bookEntity.setPublisher(request.publisher());
        bookEntity.setAuthorId(request.authorId());
        bookEntity.setPublicationDate(LocalDate.parse(request.publicationDate()));
        bookEntity.setTitle(request.title());

        return bookEntity;
    }

    public static BookResponseDto toResponseDto(BookEntity bookEntity) {
        return new BookResponseDto(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthorId(),
                bookEntity.getIsbn(),
                bookEntity.getPublisher(),
                bookEntity.getPublicationDate(),
                bookEntity.getPageCount(),
                bookEntity.getDescription(),
                bookEntity.getCreatedAt(),
                bookEntity.getUpdatedAt()
        );
    }
}
