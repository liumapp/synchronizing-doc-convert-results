package com.liumapp.demo.convert.sync.queue.publisher.service;

import com.liumapp.demo.convert.sync.queue.pattern.TestPattern;
import com.liumapp.demo.convert.sync.queue.publisher.BasicPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file TestPubliserService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
@Service
public class TestPublisherPublisher extends BasicPublisher {

    @Autowired
    private TestPattern testPattern;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void send (String msg) {
        testPattern.setSendContent(msg);
        logger.info("sender: " + testPattern.toString());
        this.sendMessage("testConsumerService", "getMessage", Integer.toString(10101), testPattern);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("test publisher get confirmed info : " + correlationData);
        if (ack) {
            logger.info("send msg success");
        } else {
            logger.error("send msg failed and the reason is : " + cause);
        }
    }

    @Override
    public void setCallBack() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

}
