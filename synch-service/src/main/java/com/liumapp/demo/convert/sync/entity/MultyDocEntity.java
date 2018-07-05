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

    private Integer convertId;

    public MultyDocEntity() {
    }

    public MultyDocEntity(String name, String content, Integer convertId) {
        this.name = name;
        this.content = content;
        this.convertId = convertId;
    }

    public Integer getConvertId() {
        return convertId;
    }

    public void setConvertId(Integer convertId) {
        this.convertId = convertId;
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
