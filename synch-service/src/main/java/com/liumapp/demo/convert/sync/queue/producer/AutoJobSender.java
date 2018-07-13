package com.liumapp.demo.convert.sync.queue.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Scheduled(cron = "* /5 * * * * *")
    public void printer () {
        System.out.println("send job per 5 seconds: " + atomicInteger.get());
        atomicInteger = new AtomicInteger(0);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
