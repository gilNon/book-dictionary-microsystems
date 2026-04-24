package com.book_system.author_service.controller;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.exception.ErrorResponseDto;
import com.book_system.author_service.service.impl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Tag(name = "Authors", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @PostMapping
    @Operation(summary = "Create author", description = "Creates a new author and returns the created record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created",
                    content = @Content(schema = @Schema(implementation = AuthorResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: access denied", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto request) {
        return new ResponseEntity<>(authorService.saveAuthor(request), HttpStatus.CREATED);
    }

    @GetMapping("/{idAuthor}")
    @Operation(summary = "Get author by ID", description = "Returns one author by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found",
                    content = @Content(schema = @Schema(implementation = AuthorResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: access denied", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<AuthorResponseDto> getAuthorById(
            @Parameter(description = "Author UUID", required = true,
                    schema = @Schema(type = "string", format = "uuid"))
            @PathVariable UUID idAuthor) {
        System.out.println("AuthorController - getAuthorById - idAuthor: " + idAuthor);
        return new ResponseEntity<>(authorService.getAuthorById(idAuthor), HttpStatus.OK);
    }

}
