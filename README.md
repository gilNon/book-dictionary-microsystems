# 📚 Book Dictionary System

Sistema básico de gestión de libros construido bajo una **arquitectura de microservicios**, diseñado para ser escalable, desacoplado y alineado a buenas prácticas modernas.

---
# ⚙️ Tecnologías usadas
- **Java 21**
- **Spring Boot 3.5**
- **Spring data JPA**
- **PostgreSQL**
- **Spring cloud gateway**




# 🏗️ Arquitectura del sistema

| Servicio | Puerto | Descripción |
|--------|--------|--------|
| Gateway | 8080 | Punto de entrada único, routing y seguridad |
| Server Discovery (Eureka) | 8030 | Registro y descubrimiento de servicios |
| Book Service | 8010 | Gestión de libros y géneros |
| Author Service | 8020 | Gestión de autores y nacionalidades |

---

## 🔹 Diagrama lógico

```
Cliente
   │
   ▼
Gateway (8080)
   │
   ▼
Eureka (8030)
   │
   ├───────────────┐
   ▼               ▼
Book Service     Author Service
(8010)           (8020)
   │               │
   ▼               ▼
Book DB        Author DB
```

---

# 🧠 Principios de diseño

- **Database per Service**
- **Desacoplamiento entre servicios**
- **API Gateway Pattern**
- **Service Discovery**

---

# 📡 API Design

## 🔹 Base URL

```
http://localhost:8080/api/v1
```

---

## 🔹 Paginación (estándar global)

Todos los endpoints `GET` soportan paginación:

| Parámetro | Descripción | Default |
|----------|------------|--------|
| `page` | Número de página | 0 |
| `size` | Cantidad de elementos | 10 |

---

## 🔹 Ejemplo básico

```bash
curl "http://localhost:8080/api/v1/books?page=0&size=10"
```

---

# 📚 Book Service

## 🔹 Crear libro

```bash
curl -X POST http://localhost:8080/book-microservice/api/v1/books \
-H "Content-Type: application/json" \
-d '{
  "title": "Clean Code",
  "authorId": "c1a2b3d4-1234-5678-9101-abcdef123456",
  "isbn": "9780132350884",
  "publisher": "Prentice Hall",
  "publicationDate": "2008-08-01",
  "edition": "1st",
  "genreId": "a1b2c3d4-5678-9101-1121-abcdef123456",
  "pageCount": 464,
  "description": "A handbook of agile software craftsmanship",
  "coverUrl": "https://example.com/clean-code.jpg"
}'
```

---

## 🔹 Obtener todos los libros (paginado)

```bash
curl "http://localhost:8080/book-microservice/api/v1/books?page=0&size=10"
```

---

## 🔹 Obtener libro por ID

```bash
curl http://localhost:8080/book-microservice/api/v1/books/{bookId}
```

---

## 🔹 Filtrar libros por autor

```bash
curl "http://localhost:8080/book-microservice/api/v1/books?authorId=c1a2b3d4-1234-5678-9101-abcdef123456&page=0&size=10"
```

---

## 🔹 Filtrar libros por género

```bash
curl "http://localhost:8080/book-microservice/api/v1/books?genreId=a1b2c3d4-5678-9101-1121-abcdef123456&page=0&size=10"
```

---

## 🔹 Filtrar por autor y género

```bash
curl "http://localhost:8080/book-microservice/api/v1/books?authorId=xxx&genreId=yyy&page=0&size=10"
```

---

## 🔹 Actualizar libro

```bash
curl -X PATCH http://localhost:8080/book-microservice/api/v1/books/{bookId} \
-H "Content-Type: application/json" \
-d '{
  "title": "Clean Code (Updated Edition)"
}'
```

---

## 🔹 Eliminar libro

```bash
curl -X DELETE http://localhost:8080/book-microservice/api/v1/books/{bookId}
```

---

# ✍️ Author Service

## 🔹 Crear autor

```bash
curl -X POST http://localhost:8080/author-microservice/api/v1/authors \
-H "Content-Type: application/json" \
-d '{
  "name": "Robert C. Martin",
  "birthDate": "1952-12-05",
  "nationalityId": "abc12345-6789-1011-1213-abcdef123456",
  "photoUrl": "https://example.com/uncle-bob.jpg"
}'
```

---

## 🔹 Obtener autores (paginado)

```bash
curl "http://localhost:8080/author-microservice/api/v1/authors?page=0&size=10"
```

---

## 🔹 Obtener autor por ID

```bash
curl http://localhost:8080/author-microservice/api/v1/authors/{authorId}
```

---

## 🔹 Actualizar autor

```bash
curl -X PATCH http://localhost:8080/author-microservice/api/v1/authors/{authorId} \
-H "Content-Type: application/json" \
-d '{
  "name": "Robert Martin"
}'
```

---

## 🔹 Eliminar autor

```bash
curl -X DELETE http://localhost:8080/author-microservice/api/v1/authors/{authorId}
```

---

# 📦 Formato de respuesta (paginación)

```json
{
  "content": [],
  "page": 0,
  "size": 10,
  "totalElements": 100,
  "totalPages": 10,
  "last": false
}
```

---

# 🗄️ Modelo de datos

## Author Service

```sql
CREATE TABLE nationality (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE author (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE,
    nationality_id UUID,
    photo_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

---

## Book Service

```sql
CREATE TABLE genre (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE book (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id UUID NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publisher VARCHAR(255),
    publication_date DATE,
    edition VARCHAR(50),
    genre_id UUID,
    page_count INTEGER,
    description TEXT,
    cover_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```


