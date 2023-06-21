package com.wileymab.bookworm.services.yaml;

import com.wileymab.bookworm.api.interfaces.AuthorServiceInterface;
import com.wileymab.bookworm.api.model.Author;
import com.wileymab.bookworm.api.services.yaml.YamlAuthorService;
import com.wileymab.bookworm.data.yaml.AuthorsYaml;
import com.wileymab.bookworm.data.yaml.YamlDataConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class YamlAuthorServiceTests {

    private AuthorServiceInterface authorsService;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
        YamlDataConfig yamlDataConfig = new YamlDataConfig("/Users/matt/Codespace/bookworm/data-yaml");
        authorsService = new YamlAuthorService(new AuthorsYaml(yamlDataConfig));
    }

    @Test
    public void getAuthorByIdIsGenerallyCorrect() {
        String id = "ba1c46cb-acab-401d-9251-a856bcc0f2a0";
        Author a = authorsService.getAuthorById(id);
        assert a.getId().equals(id);
        assert a.getName().getFirstName().equals("Douglas");
        assert a.getName().getLastName().equals("Adams");
    }

}
