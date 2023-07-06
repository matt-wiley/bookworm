package com.wileymab.bookworm.data.yaml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class YamlDataConfig {

    private static final Logger LOG = LoggerFactory.getLogger(YamlDataConfig.class);
    private final String path;

    public YamlDataConfig(@Value("${data.yaml.path}") String path) {
        this.path = path;
        LOG.info(this.path);
    }

    public String getPath() {
        return path;
    }
}
