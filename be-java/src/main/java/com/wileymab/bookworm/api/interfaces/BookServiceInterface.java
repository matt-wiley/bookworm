package com.wileymab.bookworm.api.interfaces;

import com.wileymab.bookworm.api.model.Book;

import java.util.List;


public interface BookServiceInterface {

    Book getBookById(String id);
    List<Book> getAllBooks();
    List<Book> findAllWhereTitleContains(String substring);
    List<Book> findAllWhereAuthorLastNameContains(String substring);
    List<Book> findAllWhereAuthorNameContains(String substring);

}
