package com.book_system.book_service.service.impl;

import com.book_system.book_service.controller.request.BookRequestDto;
import com.book_system.book_service.controller.response.BookResponseDetails;
import com.book_system.book_service.controller.response.BookResponseDto;
import com.book_system.book_service.controller.response.PagesDataResponse;
import com.book_system.book_service.controller.response.PaginationResponse;
import com.book_system.book_service.entity.BookEntity;
import com.book_system.book_service.exception.GeneralException;
import com.book_system.book_service.mapper.BookMapper;
import com.book_system.book_service.repository.BookRepository;
import com.book_system.book_service.restClient.AuthorRestClient;
import com.book_system.book_service.restClient.response.AuthorResponseRestClient;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRestClient authorRestClient;
    private final BookMapper bookMapper;

    public BookResponseDto saveBook(BookRequestDto request) {
        getAuthorById(request.authorId());
        BookEntity bookEntity = bookMapper.toEntity(request);

        return bookMapper.toResponseDto(saveBookEntity(bookEntity));
    }

    @Override
    public PagesDataResponse<List<BookResponseDto>> findAllBooks(Pageable pageable, UUID idAuthor, String title) {
        Page<BookEntity> bookEntityPage = findBooksByParams(pageable, idAuthor, title );

        List<BookResponseDto> bookResponseDtoList = bookEntityPage.getContent().stream()
                    .map(bookMapper::toResponseDto)
                    .toList();

        return new PagesDataResponse<>(bookResponseDtoList, Instant.now(), new PaginationResponse(bookEntityPage));
    }

    @Override
    public BookResponseDetails findBookById(UUID idBook) {
         BookEntity bookEntity = bookRepository.findById(idBook).orElseThrow(
                 () -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));

        AuthorResponseRestClient authorResponseDto = getAuthorById(bookEntity.getAuthorId());

        return bookMapper.toResponseDetailsDto(bookEntity, authorResponseDto);
    }

    @Override
    public void deleteBookById(UUID idBook) {
        BookEntity bookEntity = bookRepository.findById(idBook).orElseThrow(
                () -> new GeneralException("Book not found", HttpStatus.NOT_FOUND));
        bookEntity.setActive(false);
        bookRepository.save(bookEntity);
    }

    private Page<BookEntity> findBooksByParams(Pageable pageable, UUID idAuthor, String title) {
        Page<BookEntity> bookEntityPage;
        if (idAuthor == null && title == null) {
            return bookEntityPage = bookRepository.findAll(pageable);
        } else if (idAuthor != null && title == null) {
            return bookEntityPage = bookRepository.findAllByAuthorId(pageable, idAuthor);
        } else if(idAuthor == null) {
            return bookEntityPage = bookRepository.findAllByTitle(pageable, title);
        } else {
            return bookEntityPage = bookRepository.findAllByTitleAndAuthor(pageable,title, idAuthor);
        }
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

    private AuthorResponseRestClient getAuthorById(UUID authorId) {
        return authorRestClient.getAuthorById(authorId);
    }

}
