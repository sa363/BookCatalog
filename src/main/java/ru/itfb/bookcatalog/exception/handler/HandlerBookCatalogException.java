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
