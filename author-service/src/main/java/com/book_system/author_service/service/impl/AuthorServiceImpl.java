package com.book_system.author_service.service.impl;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.entity.AuthorEntity;
import com.book_system.author_service.entity.NationalityEntity;
import com.book_system.author_service.mapper.AuthorMapper;
import com.book_system.author_service.repository.AuthorRepository;
import com.book_system.author_service.repository.NationalityRepository;
import com.book_system.author_service.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final NationalityRepository nationalityRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDto saveAuthor(AuthorRequestDto request) {

        NationalityEntity nationality = nationalityRepository
                .findById(request.nationalityId())
                .orElseThrow(() -> new RuntimeException("Nationality not found"));

        AuthorEntity authorEntity = authorMapper.toAuthorEntity(request);
        authorEntity.setNationality(nationality);

        return authorMapper.toAuthorResponseDto(authorRepository.save(authorEntity));
    }

    @Override
    public AuthorResponseDto getAuthorById(UUID idAuthor) {
        AuthorEntity author =  authorRepository
                .findById(idAuthor)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        return authorMapper.toAuthorResponseDto(author);
    }

}
