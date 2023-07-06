package com.wileymab.bookworm.api.services.yaml;

import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.interfaces.BookServiceInterface;
import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import com.wileymab.bookworm.api.model.Book;
import com.wileymab.bookworm.api.model.Title;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YamlBookService implements BookServiceInterface {

    private final AuthorServiceInterface authorService;
    private final TitleServiceInterface titleService;


    /**
     * This service stitches together the underlying title and author services
     * to provide books
     *
     * @param authorService
     * @param titleService
     */
    public YamlBookService(
            AuthorServiceInterface authorService,
            TitleServiceInterface titleService
    ) {
        this.authorService = authorService;
        this.titleService = titleService;
    }

    @Override
    public Book getBookById(String id) {
        Title title = titleService.getTitleById(id);
        Author author = authorService.getAuthorById(title.getAuthorId());
        return new Book(title, author);
    }

    @Override
    public List<Book> getAllBooks() {
        // TODO
        return null;
    }

    @Override
    public List<Book> findAllWhereTitleContains(String substring) {
        // TODO
        return null;
    }

    @Override
    public List<Book> findAllWhereAuthorLastNameContains(String substring) {
        // TODO
        return null;
    }

    @Override
    public List<Book> findAllWhereAuthorNameContains(String substring) {
        // TODO
        return null;
    }
}
