package com.wileymab.bookworm.api.controllers;

import com.wileymab.bookworm.api.controllers.utils.RestCallHandler;
import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorsController.class);

    private final AuthorServiceInterface authorService;

    public AuthorsController(AuthorServiceInterface authorService) {
        this.authorService = authorService;
    }

    // ========================================================================
    //  PUBLIC API
    //

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable final String id) {
        return new RestCallHandler<>().execute(() -> authorService.getAuthorById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAuthors_EmptyPath() {
        return getAllAuthors();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAuthors_RootPath() {
        return getAllAuthors();
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAllAuthorsByName(@RequestParam("q") String query) {
        if (StringUtils.isEmpty(query)) {
            LOG.error("Query param value for key \"q\" was empty.");
            return ResponseEntity.badRequest().build();
        }
        // TODO: (Maybe) Paginated response handling for large results
        return new RestCallHandler<>().execute(() -> authorService.findAllAuthorsWhereNameContains(query));
    }

    @PostMapping("")
    public ResponseEntity<?> insertNewAuthor(@RequestBody Author author) {
        return new RestCallHandler<>().execute(() -> authorService.insertAuthor(author));
    }

    // ========================================================================
    //  PRIVATE API
    //

    private ResponseEntity<?> getAllAuthors() {
        // TODO: (Maybe) Paginated response handling for large results
        return new RestCallHandler<>().execute(authorService::getAllAuthors);
    }


}