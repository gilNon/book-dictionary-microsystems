package com.book_system.book_service.exception;

public class GeneralException extends RuntimeException {

    public GeneralException(Long id) {
        super("Book not found with id: " + id);
    }
}
