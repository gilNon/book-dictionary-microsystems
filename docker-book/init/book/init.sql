CREATE TABLE book(
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id UUID NOT NULL,
    isbn VARCHAR(20),
    publisher VARCHAR(255),
    publication_date DATE,
    page_count INTEGER,
    description TEXT,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT book_isbn_key UNIQUE (isbn)
);

CREATE INDEX idx_book_title
ON book(title);

CREATE INDEX idx_isbn
ON book(isbn);