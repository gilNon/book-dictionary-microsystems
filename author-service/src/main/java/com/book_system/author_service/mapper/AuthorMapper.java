package com.book_system.author_service.mapper;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorEntity toAuthorEntity(AuthorRequestDto authorRequestDto);

    AuthorResponseDto toAuthorResponseDto(AuthorEntity authorEntity);

}
