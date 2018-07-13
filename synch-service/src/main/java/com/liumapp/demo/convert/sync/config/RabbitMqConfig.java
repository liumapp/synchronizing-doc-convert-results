package com.liumapp.demo.convert.sync.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file RabbitMqConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue docConvertQueue () {
        return new Queue("doc-convert-queue");
    }



}
