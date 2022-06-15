/*
 *
 *  * Copyright 2002-2022 the Sergey Anisimov <s.anisimov@gmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 *
 */

package ru.itfb.bookcatalog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itfb.bookcatalog.model.Author;
import ru.itfb.bookcatalog.model.Book;
import ru.itfb.bookcatalog.service.impl.BookCatalogServiceImpl;

@RestController
@RequestMapping("/api")
@Slf4j
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
        log.info("Get book by id {}", id);

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
