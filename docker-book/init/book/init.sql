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

create extension if not exists pg_trgm;

create index idx_title_trgm
    on book
    using GIN(title gin_trgm_ops);

create index idx_author_id
    on book(author_id);

CREATE INDEX idx_isbn
ON book(isbn);