package com.liumapp.demo.convert.sync.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liumapp
 * @file MultyDocEntity.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@Component
public class MultyDocEntity implements Serializable {

    private String name;

    private String content;

    public MultyDocEntity() {
    }

    public MultyDocEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
