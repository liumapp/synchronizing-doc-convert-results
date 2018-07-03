package com.liumapp.demo.convert.sync.config;

import com.liumapp.demo.convert.sync.util.FileManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file FileConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@Configuration
public class FileConfig {

    @Bean
    @ConfigurationProperties(prefix = "liumapp.filemanager")
    public FileManager fileManager () {
        return new FileManager();
    }

}
