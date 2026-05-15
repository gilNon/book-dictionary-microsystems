package com.book_system.author_service.controller;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.controller.response.PagesDataResponse;
import com.book_system.author_service.exception.ErrorResponseDto;
import com.book_system.author_service.service.impl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Tag(name = "Authors", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

    @GetMapping
    @Operation(summary = "Get all authors", description = "Return all author by page")
    @Parameters(value = {
            @Parameter(name = "page", description = "Page number (0-based)",
                    schema = @Schema(type = "integer", defaultValue = "0", minimum = "0")),
            @Parameter(name = "size", description = "Number of records per page",
                    schema = @Schema(type = "integer", defaultValue = "10", minimum = "1")),
            @Parameter(name = "sort", description = "Sort criteria in the format: property,(asc|desc). Supports multiple values.",
                    schema = @Schema(type = "string", example = "name,asc"))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors found",
                    content = @Content(schema = @Schema(implementation = PagesDataResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: access denied", content = @Content)
    })
    public ResponseEntity<PagesDataResponse<List<AuthorResponseDto>>> getAllAuthor(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            @RequestParam(name = "name", required = false) String name
            ) {
        return new ResponseEntity<>(authorService.findAllAuthors(pageable, name), HttpStatus.OK);
    }

}
