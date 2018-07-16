package com.liumapp.demo.convert.sync.queue.pattern;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file QueueJobErrorInfoPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/16/18
 */
@Component
public class QueueJobErrorInfoPattern {

    private String serviceName;

    private String ErrorDesc;

    public QueueJobErrorInfoPattern() {
    }

    public QueueJobErrorInfoPattern(String serviceName, String errorDesc) {
        this.serviceName = serviceName;
        ErrorDesc = errorDesc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getErrorDesc() {
        return ErrorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        ErrorDesc = errorDesc;
    }
}
