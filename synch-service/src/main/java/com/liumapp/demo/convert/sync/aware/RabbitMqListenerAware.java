package com.liumapp.demo.convert.sync.aware;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liumapp
 * @file RabbitMqListenerAware.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
@Component
public class RabbitMqListenerAware implements ChannelAwareMessageListener, ApplicationContextAware {

    @Resource
    private MessageConverter messageConverter;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.custom.appid}")
    private String appId;

    private ApplicationContext applicationContext;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
