package com.book_system.book_service.service.impl;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.controller.response.PagesDataResponse;
import com.book_system.book_service.controller.response.PaginationResponse;
import com.book_system.book_service.entity.BookEntity;
import com.book_system.book_service.entity.GenreEntity;
import com.book_system.book_service.exception.GeneralException;
import com.book_system.book_service.mapper.BookMapper;
import com.book_system.book_service.repository.BookRepository;
import com.book_system.book_service.repository.GenreRepository;
import com.book_system.book_service.restClient.AuthorRestClient;
import com.book_system.book_service.restClient.response.AuthorResponseDto;
import com.book_system.book_service.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new GeneralException("Genre not found", HttpStatus.BAD_REQUEST));

        BookEntity bookEntity = BookMapper.toEntity(request);
        bookEntity.setGenre(genreEntity);

        return BookMapper.toResponseDto(saveBookEntity(bookEntity));

    }

    @Override
    public PagesDataResponse<List<BookResponseDto>> findAllBooks(Pageable pageable) {
        Page<BookEntity> bookEntityPage = bookRepository.findAll(pageable);
        List<BookResponseDto> bookResponseDtoList = bookEntityPage.getContent().stream()
                .map(BookMapper::toResponseDto)
                .toList();

        PaginationResponse paginationResponse = new PaginationResponse(bookEntityPage);
        return new PagesDataResponse(bookResponseDtoList, Instant.now(), new PaginationResponse(bookEntityPage));
    }

    private BookEntity saveBookEntity(BookEntity bookEntity) {
        try {
            return bookRepository.save(bookEntity);
        } catch (DataIntegrityViolationException ex) {
            log.error("Book not saved {}", ex.getMessage() );
            if (ex.getMessage().contains("book_isbn_key")) {
                throw new GeneralException("Book already exists", HttpStatus.BAD_REQUEST);
            }
            throw new GeneralException("Error saving book", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            throw new GeneralException("Error saving book", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private AuthorResponseDto getAuthorById(UUID authorId) {
        return authorRestClient.getAuthorById(authorId);
    }

}
