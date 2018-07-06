package com.liumapp.demo.convert.sync.queue.customer;

import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.demo.convert.sync.socket.ConvertingResultSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void process (String docPath) {
        logger.info("convert job begin , doc path is : " + docPath);
        doc2PDF.doc2pdf(docPath + ".pdf", docPath);
//        ConvertingResultSocketServer
    }

}
