CREATE TABLE genre(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP);

CREATE TABLE book(
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id UUID NOT NULL,
    isbn VARCHAR(20),
    publisher VARCHAR(255),
    publication_date DATE,
    edition VARCHAR(50),
    genre_id UUID NOT NULL,
    page_count INTEGER,
    description TEXT,
    cover_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT book_isbn_key UNIQUE (isbn),
    CONSTRAINT fk_genre
        FOREIGN KEY (genre_id)
        REFERENCES genre(id)
);

CREATE INDEX idx_book_title
ON book(title);

CREATE INDEX idx_isbn
ON book(isbn);

INSERT INTO genre (id, name, description, created_at, updated_at) VALUES
(gen_random_uuid(), 'Ficción', 'Obras narrativas creadas a partir de la imaginación, no basadas en hechos reales.', NOW(), NOW()),
(gen_random_uuid(), 'No Ficción', 'Obras basadas en hechos, eventos e información real.', NOW(), NOW()),
(gen_random_uuid(), 'Ciencia Ficción', 'Historias basadas en conceptos futuristas, ciencia y tecnología avanzada.', NOW(), NOW()),
(gen_random_uuid(), 'Fantasía', 'Ficción con elementos mágicos o sobrenaturales en mundos imaginarios.', NOW(), NOW()),
(gen_random_uuid(), 'Misterio', 'Historias centradas en resolver crímenes o descubrir secretos.', NOW(), NOW()),
(gen_random_uuid(), 'Suspenso', 'Historias con ritmo rápido llenas de tensión y emoción.', NOW(), NOW()),
(gen_random_uuid(), 'Romance', 'Historias centradas en relaciones amorosas.', NOW(), NOW()),
(gen_random_uuid(), 'Terror', 'Ficción diseñada para asustar o generar inquietud.', NOW(), NOW()),
(gen_random_uuid(), 'Histórico', 'Historias ambientadas en el pasado, basadas en hechos históricos.', NOW(), NOW()),
(gen_random_uuid(), 'Biografía', 'Descripción detallada de la vida de una persona escrita por otra.', NOW(), NOW()),
(gen_random_uuid(), 'Autobiografía', 'Relato de la vida de una persona escrito por ella misma.', NOW(), NOW()),
(gen_random_uuid(), 'Autoayuda', 'Libros enfocados en el desarrollo personal y bienestar.', NOW(), NOW()),
(gen_random_uuid(), 'Negocios', 'Libros sobre administración, emprendimiento y finanzas.', NOW(), NOW()),
(gen_random_uuid(), 'Tecnología', 'Libros sobre informática, desarrollo de software y tecnología.', NOW(), NOW()),
(gen_random_uuid(), 'Educación', 'Material académico y educativo para aprendizaje.', NOW(), NOW()),
(gen_random_uuid(), 'Poesía', 'Obras literarias que expresan ideas mediante ritmo y estilo.', NOW(), NOW()),
(gen_random_uuid(), 'Drama', 'Obras escritas para ser representadas como teatro.', NOW(), NOW()),
(gen_random_uuid(), 'Aventura', 'Historias de exploración, riesgo y experiencias emocionantes.', NOW(), NOW()),
(gen_random_uuid(), 'Juvenil', 'Libros dirigidos a lectores adolescentes.', NOW(), NOW()),
(gen_random_uuid(), 'Infantil', 'Libros dirigidos a niños, educativos o recreativos.', NOW(), NOW());
