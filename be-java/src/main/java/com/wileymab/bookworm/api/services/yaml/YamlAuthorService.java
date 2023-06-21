package com.wileymab.bookworm.api.services.yaml;

import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import com.wileymab.bookworm.data.yaml.AuthorsYaml;
import org.springframework.stereotype.Service;

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
        // TODO
        return null;
    }

    @Override
    public List<Author> findAllAuthorsWhereLastNameContains(String substring) {
        // TODO
        return null;
    }

    @Override
    public List<Author> findAllAuthorsWhereNameContains(String substring) {
        // TODO
        return null;
    }
}
