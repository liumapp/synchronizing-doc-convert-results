package com.liumapp.demo.convert.sync.queue.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ConverterHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Component
@RabbitListener(queues = "doc-convert-queue")
public class ConverterHandler {

    private static Logger logger = LoggerFactory.getLogger(ConverterHandler.class);

    @RabbitHandler
    public void process (String msg) throws InterruptedException {
        logger.info("a1 begin , the msg is : " + msg);
        Thread.sleep(5000);
        logger.info("a1 done");
    }

}
