package com.book_system.author_service.service;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;

public interface AuthorService {

    AuthorResponseDto saveAuthor(AuthorRequestDto request);
}
