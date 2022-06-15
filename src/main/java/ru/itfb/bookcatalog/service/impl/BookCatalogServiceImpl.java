package ru.itfb.bookcatalog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.Book;
import ru.itfb.bookcatalog.exception.AuthorNotFoundException;
import ru.itfb.bookcatalog.exception.BookNotFoundException;
import ru.itfb.bookcatalog.interfaces.AuthorRepository;
import ru.itfb.bookcatalog.interfaces.BookRepository;
import ru.itfb.bookcatalog.service.BookCatalogService;

@Service
@Slf4j
public class BookCatalogServiceImpl implements BookCatalogService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookCatalogServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("No book with id %s is available", id)));
    }

    @Override
    public Book updateBookById(Book newBook, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("No book with id %s is available", id)));
        if (newBook.getName() != null) {
            book.setName(newBook.getName());
        }
        if (newBook.getReview() != null) {
            book.setReview(newBook.getReview());
        }
        if (newBook.getStyle() != null) {
            book.setStyle(newBook.getStyle());
        }
        if (newBook.getAuthors() != null) {
            book.setAuthors(newBook.getAuthors());
            log.info("Authors: " + newBook.getAuthors().toString());

        }
        bookRepository.save(book);
        return book;
    }

    @Override
    public boolean removeBookById(Long id) {

        bookRepository.delete(
        bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException(String.format("No book with id %s is available", id))));
        return true;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public boolean deleteAuthor(Long id) {
        authorRepository.delete(
                authorRepository.findById(id).orElseThrow(() ->
                        new AuthorNotFoundException(String.format("No author with id %s is available", id))));
        return true;
    }

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(String.format("No author with id %s is available", id)));
    }
}
