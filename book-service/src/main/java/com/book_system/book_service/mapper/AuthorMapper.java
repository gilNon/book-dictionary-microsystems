package com.book_system.book_service.mapper;

import com.book_system.book_service.controller.response.AuthorResponseDto;
import com.book_system.book_service.restClient.response.AuthorResponseRestClient;

public class AuthorMapper {

    public static AuthorResponseDto toResponseDto(AuthorResponseRestClient author) {
        return new AuthorResponseDto(
                author.id(),
                author.name(),
                author.createdAt(),
                author.updatedAt()
        );
    }
}
