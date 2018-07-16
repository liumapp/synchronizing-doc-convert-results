package com.liumapp.demo.convert.sync.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.demo.convert.sync.config.ConvertConfig;
import com.liumapp.demo.convert.sync.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.convert.sync.queue.pattern.QueueJobErrorInfoPattern;
import com.liumapp.demo.convert.sync.queue.publisher.service.QueueJobErrorInfoPublisher;
import com.liumapp.demo.convert.sync.socket.ConvertingResultSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;

/**
 * @author liumapp
 * @file ConverterHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Service
public class ConverterConsumer {

    @Autowired
    private Doc2PDF doc2PDF;

    @Autowired
    private QueueJobErrorInfoPublisher queueJobErrorInfoPublisher;

    @Autowired
    private QueueJobErrorInfoPattern queueJobErrorInfoPattern;

    private static Logger logger = LoggerFactory.getLogger(ConverterConsumer.class);

    public void process (String jsonPattern) {
        logger.info("convert job begin , doc path is : " + jsonPattern);
        ConvertDocPattern docPattern = JSON.parseObject(jsonPattern, ConvertDocPattern.class);
        try {
            Thread.sleep(1500);
            doc2PDF.doc2pdf(docPattern.getPdfPath() + "/" + docPattern.getSaveName(), docPattern.getDocPath() + "/" + docPattern.getOriginalName());
            ConvertingResultSocketServer.sendStatusMessage(responseJson(docPattern), docPattern.getConvertId());
        } catch (Exception e) {
            // send msg to convert doc result that convert failed.
            queueJobErrorInfoPattern.setServiceName(ConverterConsumer.class.toString());
            queueJobErrorInfoPattern.setErrorDesc("handle doc convert failed!");
            queueJobErrorInfoPattern.setInfo(jsonPattern);
            queueJobErrorInfoPublisher.send(JSON.toJSONString(queueJobErrorInfoPattern));
        }
    }

    private JSONObject responseJson (ConvertDocPattern docPattern) {
        JSONObject object = new JSONObject();
        object.put("index", docPattern.getFileIndex());
        object.put("savename", docPattern.getSaveName());
        object.put("status", ConvertConfig.ConvertStatus.CONVERTED_SUCCESS);
        return object;
    }

}
