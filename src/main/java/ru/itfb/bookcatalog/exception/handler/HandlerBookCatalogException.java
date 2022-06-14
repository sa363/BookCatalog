package ru.itfb.bookcatalog.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itfb.bookcatalog.exception.AuthorNotFoundException;
import ru.itfb.bookcatalog.exception.BookNotFoundException;

@ControllerAdvice
public class HandlerBookCatalogException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException bookNotFoundException, WebRequest request) {
        return handleExceptionInternal(bookNotFoundException, bookNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {AuthorNotFoundException.class})
    public ResponseEntity<?> handleAuthorNotFoundException(AuthorNotFoundException authorNotFoundException, WebRequest request) {
        return handleExceptionInternal(authorNotFoundException, authorNotFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}
