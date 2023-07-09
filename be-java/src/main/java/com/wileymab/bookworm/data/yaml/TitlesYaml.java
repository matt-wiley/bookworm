package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.api.model.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TitlesYaml {

    private static class Tokens {
        private static final String DATA_SET_NAME = "titles";
        private static final String YAML_ID_KEY = "id";
        private static final String YAML_TITLE_KEY = "title";
        private static final String YAML_AUTHOR_ID_KEY = "author_id";
    }

    private static final Logger LOG = LoggerFactory.getLogger(TitlesYaml.class);

    private final String dataPath;

    private final List<Title> titlesList = new ArrayList<>();
    private final Map<String, Title> idIndex = new HashMap<>();

    public TitlesYaml(YamlDataConfig yamlDataConfig) throws FileNotFoundException {
        this.dataPath = String.format("%s/%s.yml", yamlDataConfig.getPath(), Tokens.DATA_SET_NAME);
        LOG.info(dataPath);
        loadData();
        LOG.info(titlesList.toString());
    }

    public Title getTitleById(String id) {
        return this.idIndex.get(id);
    }

    public List<Title> getAllTitles() {
        return titlesList;
    }

    public List<Title> findAllTitlesWhereTitleTextContainsString(String queryString) {
        String normalizedQueryString = queryString.toLowerCase();
        return titlesList
                .stream()
                .filter(t -> t.getTitle().toLowerCase().contains(normalizedQueryString) )
                .collect(Collectors.toList());
    }


    public Title insertTitle(Title title) throws IOException {

        Title insertableTitle = new Title();
        List<Title> updatedTitlesList = new ArrayList<>(titlesList);

        insertableTitle.setId(UUID.randomUUID().toString());
        insertableTitle.setTitle(title.getTitle());
        insertableTitle.setAuthorId(title.getAuthorId());
        updatedTitlesList.add(insertableTitle);

        refreshData(updatedTitlesList);

        return getTitleById(insertableTitle.getId());
    }

    public Title updateTitle(Title title) throws IOException {

        Title insertableTitle = new Title();
        List<Title> updatedTitlesList = allExcept(title);

        insertableTitle.setId(title.getId());
        insertableTitle.setTitle(title.getTitle());
        insertableTitle.setAuthorId(title.getAuthorId());
        updatedTitlesList.add(insertableTitle);

        refreshData(updatedTitlesList);

        return getTitleById(insertableTitle.getId());
    }

    public void removeTitleById(String id) throws IOException {
        removeTitle(getTitleById(id));
    }

    public void removeTitle(Title title) throws IOException {
        List<Title> updatedTitlesList = allExcept(title);
        refreshData(updatedTitlesList);
    }

    private List<Title> allExcept(Title title) {
        List<Title> updatedTitlesList = new ArrayList<>(titlesList);
        return updatedTitlesList.stream()
                .filter(t -> !Objects.equals(t.getId(), title.getId()))
                .collect(Collectors.toList());
    }

    private void refreshData(List<Title> updatedTitlesList) throws IOException {
        persistData(updatedTitlesList);
        loadData();
    }

    private void loadData() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(this.dataPath);
        List<Map<String, Object>> titlesRawData = yaml.load(inputStream);
        this.titlesList.clear();
        this.titlesList.addAll(deserializeData(titlesRawData));
        indexDataById();
    }

    private void persistData(List<Title> updatedTitlesList) throws IOException {
        Yaml yaml = new Yaml();
        FileWriter fileWriter = new FileWriter(this.dataPath, false);
        yaml.dump(serializeData(updatedTitlesList), fileWriter);
    }

    private List<Title> deserializeData(List<Map<String, Object>> titlesRawDataList) {
        List<Title> loadedTitlesList = new ArrayList<>();
        for (Map<String,Object> titleRawData: titlesRawDataList) {
            Title t = new Title(
                    (String) titleRawData.get(Tokens.YAML_ID_KEY),
                    (String) titleRawData.get(Tokens.YAML_TITLE_KEY),
                    (String) titleRawData.get(Tokens.YAML_AUTHOR_ID_KEY)
            );
            loadedTitlesList.add(t);
        }
        return loadedTitlesList;
    }

    private List<Map<String,Object>> serializeData(List<Title> titlesList) {
        List<Map<String,Object>> titlesRawData = new ArrayList<>();
        for ( Title title: titlesList ) {
            Map<String,Object> titleMap = new HashMap<>();
            titleMap.put(Tokens.YAML_ID_KEY, title.getId());
            titleMap.put(Tokens.YAML_AUTHOR_ID_KEY, title.getAuthorId());
            titleMap.put(Tokens.YAML_TITLE_KEY, title.getTitle());
            titlesRawData.add(titleMap);
        }
        return titlesRawData;
    }

    private void indexDataById() {
        this.idIndex.clear();
        for (Title title : this.titlesList) {
            this.idIndex.put(title.getId(), title);
        }
    }

}
