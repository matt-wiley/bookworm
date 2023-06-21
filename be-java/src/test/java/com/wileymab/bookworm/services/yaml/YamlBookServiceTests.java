package com.wileymab.bookworm.services.yaml;

import com.wileymab.bookworm.api.interfaces.BookServiceInterface;
import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Book;
import com.wileymab.bookworm.api.model.Title;
import com.wileymab.bookworm.api.services.yaml.YamlAuthorService;
import com.wileymab.bookworm.api.services.yaml.YamlBookService;
import com.wileymab.bookworm.api.services.yaml.YamlTitleService;
import com.wileymab.bookworm.data.yaml.AuthorsYaml;
import com.wileymab.bookworm.data.yaml.TitlesYaml;
import com.wileymab.bookworm.data.yaml.YamlDataConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class YamlBookServiceTests {

    private BookServiceInterface bookService;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
        YamlDataConfig yamlDataConfig = new YamlDataConfig("/Users/matt/Codespace/bookworm/data-yaml");
        YamlAuthorService authorService = new YamlAuthorService(new AuthorsYaml(yamlDataConfig));
        YamlTitleService titleService = new YamlTitleService(new TitlesYaml(yamlDataConfig));
        bookService = new YamlBookService(authorService, titleService);
    }

    @Test
    public void getBookByIdIsGenerallyCorrect() {
        String id = "cb53729d-6551-4c11-aeca-c12d535b7aa2"; // FIXME: Should book id be title id?
        Book b = bookService.getBookById(id);
        assert b.getTitle().equals("Hitchhiker's Guide to the Galaxy");
        assert b.getAuthor().getId().equals("ba1c46cb-acab-401d-9251-a856bcc0f2a0");
        assert b.getAuthor().getName().getFirstName().equals("Douglas");
        assert b.getAuthor().getName().getLastName().equals("Adams");
    }

}
