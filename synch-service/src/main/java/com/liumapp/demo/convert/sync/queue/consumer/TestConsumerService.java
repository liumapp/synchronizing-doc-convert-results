package com.liumapp.demo.convert.sync.queue.consumer;

import com.liumapp.demo.convert.sync.queue.pattern.TestPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file TestConsumerService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/16/18
 */
@Service
public class TestConsumerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TestPattern getMessage (TestPattern testPattern) {
        logger.info("test consumer service get pattern : " + testPattern.toString());
        testPattern.setReturnContent("service get job done");
        return testPattern;
    }

}
