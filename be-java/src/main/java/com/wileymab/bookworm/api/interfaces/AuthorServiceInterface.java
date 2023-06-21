package com.wileymab.bookworm.api.interfaces;

import com.wileymab.bookworm.api.model.Author;

import java.util.List;

public interface AuthorServiceInterface {

    Author getAuthorById(String id);
    List<Author> getAllAuthors();
    List<Author> findAllAuthorsWhereLastNameContains(String substring);
    List<Author> findAllAuthorsWhereNameContains(String substring);

}
