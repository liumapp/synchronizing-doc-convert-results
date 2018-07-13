package com.liumapp.demo.convert.sync.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Bean
    public Queue autoJobQueue () {
        return new Queue("auto-job-queue");
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter () {
        return new MessageListenerAdapter();
    }

    @Bean
    SimpleMessageListenerContainer container (ConnectionFactory connectionFactory,
                                              MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("auto-job-queue");
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
