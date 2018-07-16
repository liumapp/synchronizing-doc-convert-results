package com.liumapp.demo.convert.sync.queue.publisher.service;

import com.liumapp.demo.convert.sync.queue.publisher.BasicPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file ConvertDocResultPublisher.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/16/18
 */
@Service
public class QueueJobErrorInfoPublisher extends BasicPublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void send (String msg) {
        logger.info("an error has been detected : " + msg);
        this.sendMessage("queueJobErrorInfoConsumer", "handleError", Integer.toString(10103), msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("queue job error info service get confirmed info : " + correlationData);
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
