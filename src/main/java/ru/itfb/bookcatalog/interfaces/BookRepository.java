package ru.itfb.bookcatalog.interfaces;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@EnableJpaRepositories
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByAuthors(Author author);
}
