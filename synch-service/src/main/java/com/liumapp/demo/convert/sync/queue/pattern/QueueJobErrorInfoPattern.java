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

    private String info;

    public QueueJobErrorInfoPattern() {
    }

    public QueueJobErrorInfoPattern(String serviceName, String errorDesc) {
        this.serviceName = serviceName;
        ErrorDesc = errorDesc;
    }

    public QueueJobErrorInfoPattern(String serviceName, String errorDesc, String info) {
        this.serviceName = serviceName;
        ErrorDesc = errorDesc;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
