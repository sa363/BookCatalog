package ru.itfb.bookcatalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.Book;
import ru.itfb.bookcatalog.exception.AuthorNotFoundException;
import ru.itfb.bookcatalog.exception.BookNotFoundException;
import ru.itfb.bookcatalog.interfaces.AuthorRepository;
import ru.itfb.bookcatalog.interfaces.BookRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public ApiController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/author")
    Iterable<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    Author GetAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(String.
                        format("No Author with id %s is available", id))
        );
    }

    @GetMapping("/author/{id}/book")
    List<Book> GetBooksByAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return bookRepository.findAllByAuthors(author);
    }

    @PostMapping("/author")
    Author NewAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/author/{id}")
    Author NewAuthor(@PathVariable Long id, @RequestBody Author changeAuthor) {

        Author author = new Author();
        author =  authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException(String.
                        format("No author with id %s is available", id)));

        if (changeAuthor.getFullName() != null) {
            author.setFullName(changeAuthor.getFullName());
        }
        if (changeAuthor.getBooks() != null) {
            author.setBooks(changeAuthor.getBooks());
        }
        return authorRepository.save(author);
    }

    @DeleteMapping("/author/{id}")
    ResponseEntity<?> DeleteAuthor(@PathVariable Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



    @GetMapping("/book")
    Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }
    @GetMapping("/book/{id}")
    Book GetBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.
                format("No book with id %s is available", id))
        );
    }
    @PostMapping("/book")
    Book NewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
