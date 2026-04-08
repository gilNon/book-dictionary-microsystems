CREATE TABLE nationality(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE author(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE,
    nationality_id UUID NOT NULL,
    photo_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_nationality
    FOREIGN KEY (nationality_id)
    REFERENCES nationality(id)
);

CREATE INDEX idx_author_name
ON author(name);

INSERT INTO nationality (id, name, created_at, updated_at) VALUES
(gen_random_uuid(), 'Mexicana', NOW(), NOW()),
(gen_random_uuid(), 'Estadounidense', NOW(), NOW()),
(gen_random_uuid(), 'Canadiense', NOW(), NOW()),
(gen_random_uuid(), 'Brasileña', NOW(), NOW()),
(gen_random_uuid(), 'Argentina', NOW(), NOW()),
(gen_random_uuid(), 'Chilena', NOW(), NOW()),
(gen_random_uuid(), 'Colombiana', NOW(), NOW()),
(gen_random_uuid(), 'Peruana', NOW(), NOW()),
(gen_random_uuid(), 'Española', NOW(), NOW()),
(gen_random_uuid(), 'Francesa', NOW(), NOW()),
(gen_random_uuid(), 'Alemana', NOW(), NOW()),
(gen_random_uuid(), 'Italiana', NOW(), NOW()),
(gen_random_uuid(), 'Británica', NOW(), NOW()),
(gen_random_uuid(), 'Japonesa', NOW(), NOW()),
(gen_random_uuid(), 'China', NOW(), NOW()),
(gen_random_uuid(), 'India', NOW(), NOW()),
(gen_random_uuid(), 'Australiana', NOW(), NOW()),
(gen_random_uuid(), 'Sudafricana', NOW(), NOW()),
(gen_random_uuid(), 'Nigeriana', NOW(), NOW()),
(gen_random_uuid(), 'Egipcia', NOW(), NOW());
