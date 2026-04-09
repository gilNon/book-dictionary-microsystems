package com.book_system.book_service.controller;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.controller.response.PagesDataResponse;
import com.book_system.book_service.service.impl.BookServiceImpl;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto request) {
        return new ResponseEntity<>(bookService.saveBook(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagesDataResponse<List<BookResponseDto>>> findAllBooks(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return new ResponseEntity<>(bookService.findAllBooks(pageable), HttpStatus.OK);
    }

}
