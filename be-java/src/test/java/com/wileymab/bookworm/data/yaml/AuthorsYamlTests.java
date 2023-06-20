package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.data.yaml.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileNotFoundException;

public class AuthorsYamlTests {

    private AuthorsYaml authorsYaml;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
        YamlDataConfig yamlDataConfig = new YamlDataConfig("/Users/matt/Codespace/bookworm/data-yaml");
        authorsYaml = new AuthorsYaml(yamlDataConfig);
    }

    @Test
    public void getAuthorByIdIsGenerallyCorrect() {
        String id = "ba1c46cb-acab-401d-9251-a856bcc0f2a0";
        Author a = authorsYaml.getAuthorById(id);
        assert a.getId().equals(id);
        assert a.getName().getFirstName().equals("Douglas");
        assert a.getName().getLastName().equals("Adams");
    }

}
