package com.liumapp.demo.convert.sync.queue.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author liumapp
 * @file BasicPublisher.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
public abstract class BasicPublisher implements ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
