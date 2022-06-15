package ru.itfb.bookcatalog.interfaces;

import org.springframework.data.repository.CrudRepository;
import ru.itfb.bookcatalog.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
