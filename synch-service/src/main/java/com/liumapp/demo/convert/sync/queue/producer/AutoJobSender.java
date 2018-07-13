package com.liumapp.demo.convert.sync.queue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liumapp
 * @file AutoJobSender.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
@Component
public class AutoJobSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback, InitializingBean{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static Logger logger = LoggerFactory.getLogger(AutoJobSender.class);

    @Scheduled(cron = "* /5 * * * * *")
    public void printer () {
        System.out.println("send job per 5 seconds: " + atomicInteger.get());
        atomicInteger = new AtomicInteger(0);
    }

    public void send (String exchange, String routingKey, String msg, CorrelationData correlationData) {
        atomicInteger.incrementAndGet();
        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }

    /**
     * ACK=true仅仅标示消息已被Broker接收到，并不表示已成功投放至消息队列中
     * ACK=false标示消息由于Broker处理错误，消息并未处理成功
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        logger.info("job id: " + correlationData + " confirmed " + (ack ? "success" : "failed") );
    }

    /**
     * 当消息发送出去找不到对应路由队列时，将会把消息退回
     * 如果有任何一个路由队列接收投递消息成功，则不会退回消息
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.error("auto job sender failed: " + Arrays.toString(message.getBody()));
    }

    /**
     * 设置消息送达、确认的方式
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }

}
