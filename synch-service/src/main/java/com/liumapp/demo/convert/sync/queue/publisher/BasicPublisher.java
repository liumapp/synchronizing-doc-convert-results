package com.liumapp.demo.convert.sync.queue.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @author liumapp
 * @file BasicPublisher.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
public abstract class BasicPublisher implements ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    public RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.custom.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.custom.appid}")
    private String appId;

    public void sendMessage (final String serviceName, final String serviceMethodName, final String correlationId, Object request) {
        logger.info("----- send msg : [this.{}, serviceMethodName:{} serviceName:{} correlationId: {}]", this.getClass(), serviceMethodName, serviceName, correlationId);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setCorrelationKey(correlationId);
        rabbitTemplate.convertAndSend(routingKey, request, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {

                return null;
            }
        }, new CorrelationData(correlationId));
    }

    @Override
    public abstract void confirm(CorrelationData correlationData, boolean ack, String cause);
}
