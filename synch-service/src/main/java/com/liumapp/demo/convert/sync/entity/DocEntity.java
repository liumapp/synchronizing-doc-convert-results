package com.liumapp.demo.convert.sync.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liumapp
 * @file DocEntity.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@Component
public class DocEntity implements Serializable {

    private String base64File;

    public DocEntity() {
    }

    public DocEntity(String base64File) {
        this.base64File = base64File;
    }

    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File) {
        this.base64File = base64File;
    }
}
