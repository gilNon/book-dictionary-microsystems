package com.book_system.book_service.repository;

import com.book_system.book_service.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Page<BookEntity> findAllByAuthorId(Pageable pageable, UUID authorId);

    @Query(value = """
            SELECT * FROM book
            WHERE title ilike CONCAT('%', :title, '%')
    """, countQuery = """
            SELECT COUNT(*)
            FROM book
            WHERE title ILIKE CONCAT('%', :title, '%')
    """,nativeQuery = true)
    Page<BookEntity> findAllByTitle(Pageable pageable, String title);


    @Query(value = """
        SELECT *
        FROM book
        WHERE title ILIKE CONCAT('%', :title, '%')
        AND author_id = :authorId
    """, countQuery = """
        SELECT COUNT(*)
        FROM book
        WHERE title ILIKE CONCAT('%', :title, '%')
      AND author_id = :authorId
    """,
            nativeQuery = true)
    Page<BookEntity> findAllByTitleAndAuthor(Pageable pageable, String title, UUID authorId);

}
