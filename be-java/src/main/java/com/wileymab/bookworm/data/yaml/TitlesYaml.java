package com.wileymab.bookworm.data.yaml;

import com.wileymab.bookworm.data.interfaces.TitlesInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TitlesYaml implements TitlesInterface {

    private String dataPath;
    private static final Logger LOG = LoggerFactory.getLogger(TitlesYaml.class);

    public TitlesYaml(@Value("yaml.dataPath") String dataPath) {
        this.dataPath = dataPath;
        LOG.debug(dataPath);
    }

    @Override
    public Object getTitleById(Integer id) {
        return null;
    }
}
