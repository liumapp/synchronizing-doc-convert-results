package com.liumapp.demo.convert.sync.queue.pattern;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ConvertDocPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Component
public class ConvertDocPattern {

    private Integer convertId;

    private String docPath;

    private String pdfPath;

    public ConvertDocPattern() {
    }

    public ConvertDocPattern(Integer convertId, String docPath, String pdfPath) {
        this.convertId = convertId;
        this.docPath = docPath;
        this.pdfPath = pdfPath;
    }

    public Integer getConvertId() {
        return convertId;
    }

    public void setConvertId(Integer convertId) {
        this.convertId = convertId;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }
}
