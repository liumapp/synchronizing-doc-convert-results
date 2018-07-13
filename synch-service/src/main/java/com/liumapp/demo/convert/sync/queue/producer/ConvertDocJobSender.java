package com.liumapp.demo.convert.sync.queue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file AddConvertDocJob.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Component
public class ConvertDocJobSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback, InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(ConvertDocJobSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send (String jsonPattern) {
        logger.info("add new convert job, doc path is : " + jsonPattern);
        this.amqpTemplate.convertAndSend("doc-convert-queue" , jsonPattern);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
