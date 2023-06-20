package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.data.yaml.models.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.util.Objects;

public class TitlesYamlTests {

    @Mock
    private TitlesYaml titlesYaml;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
        YamlDataConfig yamlDataConfig = new YamlDataConfig("/Users/matt/Codespace/bookworm/data-yaml");
        titlesYaml = new TitlesYaml(yamlDataConfig);
    }

    @Test
    public void getTitleByIdIsGenerallyCorrect() {
        String id = "cb53729d-6551-4c11-aeca-c12d535b7aa2";
        Title t = titlesYaml.getTitleById(id);
        assert t.getId().equals(id);
        assert t.getTitle().equals("Hitchhiker's Guide to the Galaxy");
        assert t.getAuthorId().equals("ba1c46cb-acab-401d-9251-a856bcc0f2a0");
    }

}
