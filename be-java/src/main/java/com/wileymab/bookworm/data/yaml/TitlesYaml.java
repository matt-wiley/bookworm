package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.api.model.Title;
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
public class TitlesYaml {

    private static class Tokens {
        private static final String DATA_SET_NAME = "titles";
        private static final String YAML_ID_KEY = "id";
        private static final String YAML_TITLE_KEY = "title";
        private static final String YAML_AUTHOR_ID_KEY = "author_id";
    }

    private static final Logger LOG = LoggerFactory.getLogger(TitlesYaml.class);

    private String dataPath;

    private List<Title> titlesList = new ArrayList<>();
    private Map<String, Title> idIndex = new HashMap<>();

    public TitlesYaml(YamlDataConfig yamlDataConfig) throws FileNotFoundException {
        this.dataPath = String.format("%s/%s.yml", yamlDataConfig.getPath(), Tokens.DATA_SET_NAME);
        LOG.info(dataPath);
        loadYamlData();
        LOG.info(titlesList.toString());
    }

    public Title getTitleById(String id) {
        return this.idIndex.get(id);
    }


    private void loadYamlData() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(this.dataPath);
        Map<String, Object> rawData = yaml.load(inputStream);
        List<Map<String, Object>> titlesRawData = (List<Map<String, Object>>) rawData.get(Tokens.DATA_SET_NAME);
        parseData(titlesRawData);
        indexDataById();
    }

    private void parseData(List<Map<String, Object>> titlesRawDataList) {
        for (Map<String,Object> titleRawData: titlesRawDataList) {
            Title t = new Title(
                    (String) titleRawData.get(Tokens.YAML_ID_KEY),
                    (String) titleRawData.get(Tokens.YAML_TITLE_KEY),
                    (String) titleRawData.get(Tokens.YAML_AUTHOR_ID_KEY)
            );
            this.titlesList.add(t);
        }
    }

    private void indexDataById() {
        for (Title title : this.titlesList) {
            this.idIndex.put(title.getId(), title);
        }
    }

}
