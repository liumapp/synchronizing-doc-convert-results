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
import java.util.Date;
import java.util.UUID;

/**
 * @author liumapp
 * @file BasicPublisher.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
public abstract class BasicPublisher implements ConfirmCallback, RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    public RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.custom.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.custom.appid}")
    private String appId;

    public void sendMessage (final String serviceName, final String serviceMethodName, final String correlationId, Object request) {
        logger.info("----- send msg : [this.{}, serviceMethodName:{} serviceName:{} correlationId: {}]", this.getClass(), serviceMethodName, serviceName, correlationId);
        rabbitTemplate.setCorrelationKey(correlationId);
        rabbitTemplate.convertAndSend(routingKey, request, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setAppId(appId);
                message.getMessageProperties().setTimestamp(new Date());
                message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
                message.getMessageProperties().setCorrelationIdString(correlationId.toString());
                message.getMessageProperties().setHeader("ServiceMethodName", serviceMethodName);
                message.getMessageProperties().setHeader("ServiceName", serviceName);
                return message;
            }
        }, new CorrelationData(correlationId));
    }

    @Override
    public abstract void confirm(CorrelationData correlationData, boolean ack, String cause);

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.error("msg send failed , the msg content is : " + message);
        logger.error("the message code is : " + i);
        logger.error("the failed description is : " + s);
        logger.error("the exchange is : " + s1);
        logger.error("the routing key is : " + s2);
    }

    /**
     * 通知回调方法必须在子类中实现
     * 不然会触发InvocationTargetException
     */
    public abstract void setCallBack ();
}
