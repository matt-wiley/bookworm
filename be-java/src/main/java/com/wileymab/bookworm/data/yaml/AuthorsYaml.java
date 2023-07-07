package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.api.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

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

    private final String dataPath;

    private final List<Author> authorsList = new ArrayList<>();
    private final Map<String, Author> idIndex = new HashMap<>();

    public AuthorsYaml(YamlDataConfig yamlDataConfig) throws FileNotFoundException {
        this.dataPath = String.format("%s/%s.yml", yamlDataConfig.getPath(), Tokens.DATA_SET_NAME);
        LOG.info(dataPath);
        loadData();
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

    public Author insertAuthor(Author author) throws IOException {
        Author insertableAuthor = new Author();
        List<Author> updatedAuthorsList = new ArrayList<>(authorsList);

        insertableAuthor.setName(author.getName());
        insertableAuthor.setId(UUID.randomUUID().toString());
        updatedAuthorsList.add(insertableAuthor);

        persistData(updatedAuthorsList);
        loadData();

        return getAuthorById(insertableAuthor.getId());
    }

    private void loadData() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(this.dataPath);
        List<Map<String, Object>> authorsRawDataList = yaml.load(inputStream);
        this.authorsList.clear();
        this.authorsList.addAll(deserializeData(authorsRawDataList));
        indexDataById();
    }

    private void persistData(List<Author> updatedAuthorsList) throws IOException {
        Yaml yaml = new Yaml();
        FileWriter fileWriter = new FileWriter(this.dataPath, false);
        yaml.dump(serializeData(updatedAuthorsList), fileWriter);
    }

    private List<Author> deserializeData(List<Map<String, Object>> authorsRawDataList) {
        List<Author> loadedAuthorsList = new ArrayList<>();
        for (Map<String,Object> authorRawData: authorsRawDataList) {
            Map<String, String> nameMap = (Map<String, String>) authorRawData.get(Tokens.YAML_NAME_KEY);
            Author a = new Author(
                    (String) authorRawData.get(Tokens.YAML_ID_KEY),
                    new Author.Name(
                            nameMap.get(Tokens.YAML_NAME_FIRST_KEY),
                            nameMap.get(Tokens.YAML_NAME_LAST_KEY)
                    )
            );
            loadedAuthorsList.add(a);
        }
        return loadedAuthorsList;
    }

    private List<Map<String,Object>> serializeData(List<Author> authorsList) {
        List<Map<String,Object>> authorsRawData = new ArrayList<>();
        for ( Author author: authorsList ) {
            Map<String,Object> authorMap = new HashMap<>();
            authorMap.put(Tokens.YAML_ID_KEY, author.getId());

            Map<String,String> nameMap = new HashMap<>();
            nameMap.put(Tokens.YAML_NAME_FIRST_KEY, author.getName().getFirstName());
            nameMap.put(Tokens.YAML_NAME_LAST_KEY, author.getName().getLastName());
            authorMap.put(Tokens.YAML_NAME_KEY, nameMap);

            authorsRawData.add(authorMap);
        }
        return authorsRawData;
    }

    private void indexDataById() {
        this.idIndex.clear();
        for ( Author author: this.authorsList ) {
            this.idIndex.put(author.getId(), author);
        }
    }

}
