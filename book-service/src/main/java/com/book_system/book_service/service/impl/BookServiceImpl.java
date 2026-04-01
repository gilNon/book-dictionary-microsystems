package com.book_system.book_service.service.impl;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.entity.BookEntity;
import com.book_system.book_service.mapper.BookMapper;
import com.book_system.book_service.repository.BookRepository;
import com.book_system.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookResponseDto saveBook(BookRequestDto request) {
        BookEntity bookEntity = BookMapper.toEntity(request);
        BookEntity savedEntity = bookRepository.save(bookEntity);
        return BookMapper.toResponse(savedEntity);
    }

}
