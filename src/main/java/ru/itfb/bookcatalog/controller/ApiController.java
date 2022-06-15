package ru.itfb.bookcatalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.Book;
import ru.itfb.bookcatalog.service.impl.BookCatalogServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BookCatalogServiceImpl service;

    public ApiController(BookCatalogServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/book")
    Book NewBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/book/{id}")
    Book GetBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PutMapping("/book/{id}")
    Book updateBookById(@PathVariable long id, @RequestBody Book book) {
        return service.updateBookById(book, id);
    }

    @DeleteMapping("/book/{id}")
    ResponseEntity<?> removeBookById(@PathVariable long id) {
        return service.removeBookById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }


    @GetMapping("/author")
    Iterable<Author> allAuthors() {
        return service.getAllAuthors();
    }

    @GetMapping("/author/{id}")
    Author GetAuthorById(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    @PostMapping("/author")
    Author NewAuthor(@RequestBody Author author) {
        return service.addAuthor(author);
    }

    @DeleteMapping("/author/{id}")
    ResponseEntity<?> DeleteAuthor(@PathVariable Long id) {
        return service.deleteAuthor(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }








}
