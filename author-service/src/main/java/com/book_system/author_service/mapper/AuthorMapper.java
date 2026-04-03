package com.book_system.author_service.mapper;

import com.book_system.author_service.controller.request.AuthorRequestDto;
import com.book_system.author_service.controller.response.AuthorResponseDto;
import com.book_system.author_service.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorEntity toAuthorEntity(AuthorRequestDto authorRequestDto);

    @Mapping(target = "nationality", source = "nationality.name")
    AuthorResponseDto toAuthorResponseDto(AuthorEntity authorEntity);

}
