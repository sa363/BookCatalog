package ru.itfb.bookcatalog.service;

import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.Book;

public interface BookCatalogService {

    Book addBook(Book book);
    Book getBookById(long id);
    Book updateBookById(Book newBook, Long id);
    boolean removeBookById(Long id);

    Author addAuthor(Author author);
    boolean deleteAuthor(Long id);

    Iterable<Author> getAllAuthors();

    Author getAuthorById(Long id);
}
