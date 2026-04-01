package com.book_system.author_service.controller;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.service.impl.AuthorServiceImpl;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto request) {
        return new ResponseEntity<>(authorService.create(request), HttpStatus.CREATED);
    }

}
