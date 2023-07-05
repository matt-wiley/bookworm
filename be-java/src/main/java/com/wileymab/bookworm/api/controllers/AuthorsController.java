package com.wileymab.bookworm.api.controllers;

import com.wileymab.bookworm.api.controllers.utils.RestCallHandler;
import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorsController.class);

    private final AuthorServiceInterface authorService;

    public AuthorsController(AuthorServiceInterface authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable final String id) {
        return new RestCallHandler<>().execute(() -> authorService.getAuthorById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAuthors() {
        return new RestCallHandler<>().execute(authorService::getAllAuthors);
    }

}