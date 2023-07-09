package com.wileymab.bookworm.api.services.yaml;

import com.wileymab.bookworm.api.interfaces.TitleServiceInterface;
import com.wileymab.bookworm.api.model.Title;
import com.wileymab.bookworm.data.yaml.TitlesYaml;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
        return titlesYamlData.findAllTitlesWhereTitleTextContainsString(substring);
    }

    @Override
    public Title insertTitle(Title title) throws IOException {
        return titlesYamlData.insertTitle(title);
    }

    @Override
    public Title updateTitle(Title title) throws IOException {
        return titlesYamlData.updateTitle(title);
    }

    @Override
    public void removeTitle(Title title) throws IOException {
        titlesYamlData.removeTitle(title);
    }

    @Override
    public void removeTitleById(String id) throws IOException {
        titlesYamlData.removeTitleById(id);
    }


}
