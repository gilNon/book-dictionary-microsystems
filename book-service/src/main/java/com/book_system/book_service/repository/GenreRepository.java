package com.book_system.book_service.repository;

import com.book_system.book_service.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {
}
