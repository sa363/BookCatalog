package ru.itfb.bookcatalog.controller;

import org.springframework.web.bind.annotation.*;
import ru.itfb.bookcatalog.Author;
import ru.itfb.bookcatalog.interfaces.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    List<Author> allAuthors() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Author> GetAuthorById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    Author NewAuthor(@RequestBody Author author) {
        return repository.saveAndFlush(author);
    }
}
