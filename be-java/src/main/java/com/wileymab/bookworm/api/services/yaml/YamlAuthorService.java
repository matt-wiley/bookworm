package com.wileymab.bookworm.api.services.yaml;

import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import com.wileymab.bookworm.data.yaml.AuthorsYaml;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class YamlAuthorService implements AuthorServiceInterface {

    private final AuthorsYaml authorsYamlData;

    public YamlAuthorService(AuthorsYaml authorsYamlData) {
        this.authorsYamlData = authorsYamlData;
    }

    @Override
    public Author getAuthorById(String id) {
        return authorsYamlData.getAuthorById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorsYamlData.getAllAuthors();
    }

    @Override
    public List<Author> findAllAuthorsWhereNameContains(String substring) {
        return authorsYamlData.findAllAuthorsWhereNameContainsString(substring);
    }

    @Override
    public Author insertAuthor(Author author) throws IOException {
        return authorsYamlData.insertAuthor(author);
    }

}
