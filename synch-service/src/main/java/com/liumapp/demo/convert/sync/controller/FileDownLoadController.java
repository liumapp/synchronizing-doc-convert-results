package com.liumapp.demo.convert.sync.controller;

import com.liumapp.demo.convert.sync.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author liumapp
 * @file FileDownLoadController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@RestController
@RequestMapping("download")
public class FileDownLoadController {

    @Autowired
    private FileManager fileManager;

    @RequestMapping(value = "/")
    public ResponseEntity<FileSystemResource> download (@RequestParam String filename) {
        return fileManager.exportDownloadFile(new File(fileManager.getSavePath() + "/" + filename));
    }

}
