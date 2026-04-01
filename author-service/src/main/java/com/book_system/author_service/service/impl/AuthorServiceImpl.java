package com.book_system.author_service.service.impl;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.entity.AuthorEntity;
import com.book_system.author_service.mapper.AuthorMapper;
import com.book_system.author_service.repository.AuthorRepository;
import com.book_system.author_service.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponseDto saveAuthor(AuthorRequestDto request) {
        AuthorEntity authorEntity = AuthorMapper.toEntity(request);
        AuthorEntity savedEntity = authorRepository.save(authorEntity);
        return AuthorMapper.toResponse(savedEntity);
    }

}
