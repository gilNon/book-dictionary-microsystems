package com.book_system.book_service.repository;

import com.book_system.book_service.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Page<BookEntity> findAllByAuthorId(Pageable pageable, UUID authorId);
}
