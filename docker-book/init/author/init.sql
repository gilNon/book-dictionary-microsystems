CREATE TABLE author(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

create extension if not exists pg_trgm;

create index idx_name_trgm
    on author
    using GIN(name gin_trgm_ops);