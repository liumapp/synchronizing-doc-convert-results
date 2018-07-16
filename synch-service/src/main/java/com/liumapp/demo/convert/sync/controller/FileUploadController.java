package com.liumapp.demo.convert.sync.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.demo.convert.sync.entity.DocEntity;
import com.liumapp.demo.convert.sync.entity.MultyDocEntity;
import com.liumapp.demo.convert.sync.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.convert.sync.queue.publisher.service.ConvertDocPublisher;
import com.liumapp.demo.convert.sync.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author liumapp
 * @file FileUpload.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@RestController
@RequestMapping("upload")
public class FileUploadController {

    @Autowired
    private FileManager fileManager;

    @Autowired
    private ConvertDocPublisher convertDocPublisher;

    @Autowired
    private ConvertDocPattern convertDocPattern;

    @RequestMapping("/")
    public String upload (@RequestParam("file") MultipartFile file) throws IOException {
        fileManager.save(file);
        return JSON.toJSONString("success");
    }

    @RequestMapping("/base64")
    public String base64Upload (@RequestBody DocEntity docEntity) {
        try {
            MultipartFile file = fileManager.base64toMultipart(docEntity.getBase64File());
            fileManager.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("save file failed");
        }
        return JSON.toJSONString("success");
    }

    @RequestMapping("/multybase64")
    @ResponseBody
    public String multyBase64Upload (@RequestBody MultyDocEntity[] list) {
        try {
            int index = 0;
            for (MultyDocEntity doc : list) {
                MultipartFile file = fileManager.base64toMultipart(doc.getContent());
                fileManager.save(file);
                convertDocPattern.setFileIndex(index);
                convertDocPattern.setConvertId(doc.getConvertId());
                convertDocPattern.setDocPath(fileManager.getSavePath());
                convertDocPattern.setPdfPath(fileManager.getSavePath());
                convertDocPattern.setOriginalName(fileManager.getFileName());
                convertDocPattern.setSaveName(fileManager.getFileName() + ".pdf");
                convertDocPublisher.send(JSON.toJSONString(convertDocPattern));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("error");
        }
        return JSON.toJSONString("success");
    }

}
