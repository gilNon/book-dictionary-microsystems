package com.book_system.author_service.service;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;

import java.util.UUID;

public interface AuthorService {

    AuthorResponseDto saveAuthor(AuthorRequestDto request);

    AuthorResponseDto getAuthorById(UUID idAuthor);
}
