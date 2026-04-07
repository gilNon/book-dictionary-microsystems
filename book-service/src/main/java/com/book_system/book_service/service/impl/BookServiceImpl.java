package com.book_system.book_service.service.impl;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.entity.BookEntity;
import com.book_system.book_service.entity.GenreEntity;
import com.book_system.book_service.mapper.BookMapper;
import com.book_system.book_service.repository.BookRepository;
import com.book_system.book_service.repository.GenreRepository;
import com.book_system.book_service.restClient.AuthorRestClient;
import com.book_system.book_service.restClient.response.AuthorResponseDto;
import com.book_system.book_service.service.BookService;
import feign.FeignException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRestClient authorRestClient;

    public BookResponseDto saveBook(BookRequestDto request) {
        AuthorResponseDto authorResponseDto = getAuthorById(request.authorId());

        GenreEntity genreEntity = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        BookEntity bookEntity = BookMapper.toEntity(request);
        bookEntity.setGenre(genreEntity);

        return BookMapper.toResponseDto(saveBookEntity(bookEntity));

    }

    private BookEntity saveBookEntity(BookEntity bookEntity) {
        try {
            return bookRepository.save(bookEntity);
        } catch (DataIntegrityViolationException ex) {
            log.error("Book not saved {}", ex.getMessage() );
            if (ex.getMessage().contains("book_isbn_key")) {
                throw new RuntimeException("Book already exists");
            }
            throw new RuntimeException("Error saving book");
        } catch (Exception ex) {
            throw new RuntimeException("Error saving book");
        }

    }
    private AuthorResponseDto getAuthorById(UUID authorId) {
        try {
            return authorRestClient.getAuthorById(authorId);
        } catch (FeignException.NotFound ex) {
            throw new RuntimeException("Author not found");
        } catch (Exception ex) {
            throw new RuntimeException("Error calling author service");
        }
    }

}
