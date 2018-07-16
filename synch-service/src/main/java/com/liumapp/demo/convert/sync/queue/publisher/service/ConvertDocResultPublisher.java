package com.liumapp.demo.convert.sync.queue.publisher.service;

import com.liumapp.demo.convert.sync.queue.publisher.BasicPublisher;
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
public class ConvertDocResultPublisher extends BasicPublisher {



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}
