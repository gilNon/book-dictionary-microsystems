package com.book_system.author_service.repository;

import com.book_system.author_service.entity.NationalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NationalityRepository extends JpaRepository<NationalityEntity, UUID> {
}
