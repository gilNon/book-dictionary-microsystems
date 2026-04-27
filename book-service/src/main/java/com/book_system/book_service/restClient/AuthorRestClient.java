package com.book_system.book_service.restClient;

import com.book_system.book_service.restClient.response.AuthorResponseRestClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "author-microservice", fallbackFactory = AuthorFallBack.class)
public interface AuthorRestClient {

    @GetMapping("/api/v1/authors/{idAuthor}")
    AuthorResponseRestClient getAuthorById(@PathVariable UUID idAuthor);
}
