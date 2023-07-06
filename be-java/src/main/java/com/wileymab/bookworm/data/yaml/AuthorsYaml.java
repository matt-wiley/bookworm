package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.api.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorsYaml {

    private static class Tokens {
        private static final String DATA_SET_NAME = "authors";
        private static final String YAML_ID_KEY = "id";
        private static final String YAML_NAME_KEY = "name";
        private static final String YAML_NAME_FIRST_KEY = "first";
        private static final String YAML_NAME_LAST_KEY = "last";
    }

    private static final Logger LOG = LoggerFactory.getLogger(AuthorsYaml.class);

    private String dataPath;

    private List<Author> authorsList = new ArrayList<>();
    private Map<String, Author> idIndex = new HashMap<>();

    public AuthorsYaml(YamlDataConfig yamlDataConfig) throws FileNotFoundException {
        this.dataPath = String.format("%s/%s.yml", yamlDataConfig.getPath(), Tokens.DATA_SET_NAME);
        LOG.info(dataPath);
        loadYamlData();
        LOG.info(authorsList.toString());
    }

    public Author getAuthorById(String id) {
        return this.idIndex.get(id);
    }

    public List<Author> getAllAuthors() {
        return authorsList;
    }

    public List<Author> findAllAuthorsWhereNameContainsString(String queryString) {
        String normalizedQueryString = queryString.toLowerCase();
        return authorsList
                .stream()
                .filter(a ->
                        a.getName().getFirstName().toLowerCase().contains(normalizedQueryString) ||
                        a.getName().getLastName().toLowerCase().contains(normalizedQueryString))
                .toList();
    }


    private void loadYamlData() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(this.dataPath);
        Map<String, Object> rawData = yaml.load(inputStream);
        List<Map<String, Object>> authorsRawDataList = (List<Map<String, Object>>) rawData.get(Tokens.DATA_SET_NAME);
        parseData(authorsRawDataList);
        indexDataById();
    }

    private void parseData(List<Map<String, Object>> authorsRawDataList) {
        for (Map<String,Object> authorRawData: authorsRawDataList) {
            Map<String, String> nameMap = (Map<String, String>) authorRawData.get(Tokens.YAML_NAME_KEY);
            Author t = new Author(
                    (String) authorRawData.get(Tokens.YAML_ID_KEY),
                    new Author.Name(
                            nameMap.get(Tokens.YAML_NAME_FIRST_KEY),
                            nameMap.get(Tokens.YAML_NAME_LAST_KEY)
                    )
            );
            this.authorsList.add(t);
        }
    }

    private void indexDataById() {
        for ( Author author: this.authorsList ) {
            this.idIndex.put(author.getId(), author);
        }
    }
}
