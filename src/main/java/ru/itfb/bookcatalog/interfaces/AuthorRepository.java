package ru.itfb.bookcatalog.interfaces;

import org.springframework.data.repository.CrudRepository;
import ru.itfb.bookcatalog.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
