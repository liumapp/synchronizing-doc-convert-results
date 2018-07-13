package com.liumapp.demo.convert.sync.queue.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.demo.convert.sync.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.convert.sync.socket.ConvertingResultSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

/**
 * @author liumapp
 * @file ConverterHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Component
@RabbitListener(queues = "doc-convert-queue")
public class ConverterHandler {

    @Autowired
    private Doc2PDF doc2PDF;

    private static Logger logger = LoggerFactory.getLogger(ConverterHandler.class);

    @RabbitHandler
    public void process (String jsonPattern, Channel channel) throws Exception {
        logger.info("convert job begin , doc path is : " + jsonPattern);
        ConvertDocPattern docPattern = JSON.parseObject(jsonPattern, ConvertDocPattern.class);
        Thread.sleep(1500);
        doc2PDF.doc2pdf(docPattern.getPdfPath() + "/" + docPattern.getSaveName(), docPattern.getDocPath() + "/" + docPattern.getOriginalName());
        ConvertingResultSocketServer.sendMessage(responseJson(docPattern), docPattern.getConvertId());
    }

    private String responseJson (ConvertDocPattern docPattern) {
        JSONObject object = new JSONObject();
        object.put("index", docPattern.getFileIndex());
        object.put("savename", docPattern.getSaveName());
        return JSON.toJSONString(object);
    }

}
