package com.book_system.book_service.controller;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.service.impl.BookServiceImpl;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto create(@Valid @RequestBody BookRequestDto request) {
        return bookService.create(request);
    }

}
