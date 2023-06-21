package com.wileymab.bookworm.services.yaml;

import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Title;
import com.wileymab.bookworm.api.services.yaml.YamlTitleService;
import com.wileymab.bookworm.data.yaml.TitlesYaml;
import com.wileymab.bookworm.data.yaml.YamlDataConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class YamlTitleServiceTests {

    private TitleServiceInterface titleService;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException {
        YamlDataConfig yamlDataConfig = new YamlDataConfig("/Users/matt/Codespace/bookworm/data-yaml");
        titleService = new YamlTitleService(new TitlesYaml(yamlDataConfig));
    }

    @Test
    public void getTitleByIdIsGenerallyCorrect() {
        String id = "cb53729d-6551-4c11-aeca-c12d535b7aa2";
        Title t = titleService.getTitleById(id);
        assert t.getId().equals(id);
        assert t.getTitle().equals("Hitchhiker's Guide to the Galaxy");
        assert t.getAuthorId().equals("ba1c46cb-acab-401d-9251-a856bcc0f2a0");
    }

}
