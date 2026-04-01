package com.book_system.book_service.service;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;

public interface BookService {

    BookResponseDto saveBook(BookRequestDto request);
}
