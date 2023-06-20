package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.data.interfaces.AuthorsInterface;
import com.wileymab.bookworm.data.yaml.models.Author;
import com.wileymab.bookworm.data.yaml.models.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AuthorsYaml implements AuthorsInterface<Author> {

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


    public AuthorsYaml(YamlDataConfig yamlDataConfig) throws FileNotFoundException {
        this.dataPath = String.format("%s/%s.yml", yamlDataConfig.getPath(), Tokens.DATA_SET_NAME);
        LOG.info(dataPath);
        loadYamlData();
        LOG.info(authorsList.toString());
    }

    @Override
    public Author getAuthorById(Integer id) {
        return null;
    }

    private void loadYamlData() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(this.dataPath);
        Map<String, Object> rawData = yaml.load(inputStream);
        List<Map<String, Object>> authorsRawDataList = (List<Map<String, Object>>) rawData.get(Tokens.DATA_SET_NAME);
        parseData(authorsRawDataList);
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

}
