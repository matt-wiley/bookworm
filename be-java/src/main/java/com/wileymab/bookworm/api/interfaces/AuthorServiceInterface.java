package com.wileymab.bookworm.api.interfaces;

import com.wileymab.bookworm.api.model.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorServiceInterface {

    Author getAuthorById(String id);
    List<Author> getAllAuthors();
    List<Author> findAllAuthorsWhereNameContains(String substring);

    Author insertAuthor(Author author) throws IOException;

}
