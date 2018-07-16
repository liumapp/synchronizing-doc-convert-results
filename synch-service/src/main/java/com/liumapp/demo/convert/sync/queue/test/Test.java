package com.liumapp.demo.convert.sync.queue.test;

import com.liumapp.demo.convert.sync.queue.publisher.service.TestPublisherPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file Test.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/14/18
 */
@Component
@EnableScheduling
public class Test {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestPublisherPublisher testPublisherPublisher;

    /**
     * on prod model
     * plz annotate thie method
     */
//    @Scheduled(fixedDelay = 3000)
//    public void send () {
//        logger.info("test begin ");
//        testPublisherService.send("send test msg at : " + new Date());
//    }

}
