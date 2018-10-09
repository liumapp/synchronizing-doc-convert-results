package com.liumapp.demo.convert.sync;

import com.liumapp.qtools.starter.springboot.QtoolsSpringBootStarter;
import com.liumapp.rabbitmq.RabbitMQOperatorMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author liumapp
 * @file SynchronizeMain.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/2/18
 */
@SpringBootApplication
@Import({RabbitMQOperatorMain.class, QtoolsSpringBootStarter.class})
public class SynchronizeMain {

    public static void main (String[] args) {
        SpringApplication.run(SynchronizeMain.class, args);
    }

}
