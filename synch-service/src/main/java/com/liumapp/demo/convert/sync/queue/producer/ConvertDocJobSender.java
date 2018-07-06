package com.liumapp.demo.convert.sync.queue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
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
public class ConvertDocJobSender {

    private static Logger logger = LoggerFactory.getLogger(ConvertDocJobSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send (String msg) {
        this.amqpTemplate.convertAndSend("test-queue" , "content");
    }

}
