package ru.itfb.bookcatalog.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itfb.bookcatalog.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
