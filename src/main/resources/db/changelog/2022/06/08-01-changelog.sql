-- liquibase formatted sql

-- changeset Sergey:1654685587690-1
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

-- changeset Sergey:1654685587690-2
CREATE TABLE author
(
    id        BIGINT NOT NULL,
    full_name VARCHAR(255),
    CONSTRAINT pk_author PRIMARY KEY (id)
);

-- changeset Sergey:1654685587690-3
CREATE TABLE author_books
(
    author_id BIGINT NOT NULL,
    books_id  BIGINT NOT NULL,
    CONSTRAINT pk_author_books PRIMARY KEY (author_id, books_id)
);

-- changeset Sergey:1654685587690-4
CREATE TABLE book
(
    id     BIGINT NOT NULL,
    name   VARCHAR(255),
    review VARCHAR(255),
    CONSTRAINT pk_book PRIMARY KEY (id)
);

-- changeset Sergey:1654685587690-5
ALTER TABLE author
    ADD CONSTRAINT uc_author_full_name UNIQUE (full_name);

-- changeset Sergey:1654685587690-6
ALTER TABLE author_books
    ADD CONSTRAINT fk_autboo_on_author FOREIGN KEY (author_id) REFERENCES author (id);

-- changeset Sergey:1654685587690-7
ALTER TABLE author_books
    ADD CONSTRAINT fk_autboo_on_book FOREIGN KEY (books_id) REFERENCES book (id);

