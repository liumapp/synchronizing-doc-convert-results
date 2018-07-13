package com.liumapp.demo.convert.sync.queue.publisher;

import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author liumapp
 * @file TestPubliserService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
public class TestPubliserService extends BasicPublisher {


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}
