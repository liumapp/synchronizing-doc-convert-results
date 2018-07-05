package com.liumapp.demo.convert.sync.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;

/**
 * @author liumapp
 * @file FileManager.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@Component
public class FileManager {

    private String savePath;

    private String destFilePath;

    public void save (MultipartFile file) throws IOException {
        this.destFilePath = savePath + file.getOriginalFilename();
        File destFile = new File(this.destFilePath);
        file.transferTo(destFile);
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public MultipartFile base64toMultipart (String base64) {
        return this.base64File(base64);
    }

    public Base64File base64File (String base64) {
        try {
            String[] baseStr = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for (int i = 0 ; i < b.length ; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64File(b, baseStr[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDestFilePath() {
        return destFilePath;
    }

}
