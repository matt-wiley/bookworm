package com.wileymab.bookworm.api.controllers;

import com.wileymab.bookworm.api.controllers.utils.RestCallHandler;
import com.wileymab.bookworm.api.interfaces.BookServiceInterface;
import com.wileymab.bookworm.api.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/books")
public class BooksController {

    private static final Logger LOG = LoggerFactory.getLogger(BooksController.class);

    private final BookServiceInterface bookService;

    public BooksController(BookServiceInterface bookService) {
        this.bookService = bookService;
    }

    // ========================================================================
    //  PUBLIC API
    //

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getAllBooks_EmptyPath() {
        return getAllBooks();
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getAllBooks_RootPath() {
        return getAllBooks();
    }



    // ========================================================================
    //  PRIVATE API
    //

    private ResponseEntity<?> getAllBooks() {
        ResponseEntity<?> response = new RestCallHandler<List<Book>>().execute(bookService::getAllBooks);
        return response;
    }
}
