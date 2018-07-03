package com.liumapp.demo.convert.sync.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author liumapp
 * @file Base64File.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
public class Base64File implements MultipartFile {

    private final byte[] content;

    private final String header;

    public Base64File(byte[] content, String header) {
        this.content = content;
        this.header = header.split(";")[0];
    }

    public byte[] getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return content == null || content.length == 0;
    }

    @Override
    public long getSize() {
        return content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(content);
    }
}
