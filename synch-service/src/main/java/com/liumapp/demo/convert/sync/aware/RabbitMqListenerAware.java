package com.liumapp.demo.convert.sync.aware;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ReturnListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    private static Logger logger = LoggerFactory.getLogger(RabbitMqListenerAware.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("----- received " + message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        try {
            Object msg = messageConverter.fromMessage(message);
            if (!appId.equals(message.getMessageProperties().getAppId())) {
                throw new SecurityException("unknow app Id : " + message.getMessageProperties().getAppId());
            }
            Object service = applicationContext.getBean(message.getMessageProperties().getHeaders().get("ServiceName").toString());
            String serviceMethodName = message.getMessageProperties().getHeaders().get("ServiceMethodName").toString();
            Method method = service.getClass().getMethod(serviceMethodName, msg.getClass());
            method.invoke(service, msg);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error("-------- err " + e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
