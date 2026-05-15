package com.book_system.book_service.restClient;

import com.book_system.book_service.exception.GeneralException;
import com.book_system.book_service.restClient.response.AuthorResponseRestClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class AuthorFallBack implements FallbackFactory<AuthorRestClient> {

    @Override
    public AuthorRestClient create(Throwable cause) {

        return new AuthorRestClient() {
            @Override
            public AuthorResponseRestClient getAuthorById(UUID idAuthor, String authorization) {
                if (cause instanceof FeignException.NotFound) {
                    throw new GeneralException("Author not found", HttpStatus.NOT_FOUND);
                }

                log.error("Author service fallback triggered for authorId {}", idAuthor, cause);
                throw new GeneralException("Author service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
            }

        };
    }
}
