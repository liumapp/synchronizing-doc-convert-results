package com.liumapp.demo.convert.sync.queue.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(fixedDelay = 1000)
    public void send () {
        logger.info("test begin ");
    }

}
