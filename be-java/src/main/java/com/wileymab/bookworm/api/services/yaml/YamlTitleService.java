package com.wileymab.bookworm.api.services.yaml;

import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Title;
import com.wileymab.bookworm.data.yaml.TitlesYaml;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YamlTitleService implements TitleServiceInterface {

    private final TitlesYaml titlesYamlData;

    public YamlTitleService(TitlesYaml titlesYamlData) {
        this.titlesYamlData = titlesYamlData;
    }

    @Override
    public Title getTitleById(String id) {
        return titlesYamlData.getTitleById(id);
    }

    @Override
    public List<Title> getAllTitles() {
        return titlesYamlData.getAllTitles();
    }


    @Override
    public List<Title> findAllWhereTitleContains(String substring) {
        // TODO
        return null;
    }

    @Override
    public List<Title> findAllWhereAuthorIdEquals(String substring) {
        // TODO
        return null;
    }
}
