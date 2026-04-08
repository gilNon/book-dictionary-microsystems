package com.book_system.book_service.restClient;

import com.book_system.book_service.restClient.response.AuthorResponseDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorFallBack implements AuthorRestClient {

    @Override
    public AuthorResponseDto getAuthorById(UUID idAuthor) {

        throw new RuntimeException("Author not found");
    }
}
