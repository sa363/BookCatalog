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

package ru.itfb.bookcatalog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itfb.bookcatalog.model.Author;
import ru.itfb.bookcatalog.model.Book;
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
