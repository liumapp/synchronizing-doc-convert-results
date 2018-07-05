package com.liumapp.demo.convert.sync.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author liumapp
 * @file ConvertingResultSocketServer.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/5/18
 */
@ServerEndpoint(value = "convertingSocket/{convertId}")
@Component
public class ConvertingResultSocketServer {

    private static Logger logger = LoggerFactory.getLogger(ConvertingResultSocketServer.class);

    private static CopyOnWriteArraySet<ConvertingResultSocketServer> resultWebSet = new CopyOnWriteArraySet<ConvertingResultSocketServer>();

    private Session session;

    private String convertId;

    @OnOpen
    public void onOpen (Session session, @PathParam(value = "convertId") String convertId) {
        this.session = session;
        this.convertId = convertId;
        resultWebSet.add(this);
        logger.info("new  convert job in , the convertId is :" + convertId);
    }

    @OnClose
    public void onClose () {
        resultWebSet.remove(this);
        logger.info("convert job out , the convertId is :" + this.convertId);
    }

    @OnMessage
    public void onMessage (String msg) {
        logger.info("convert info: " + msg + ", the convertId is : " + this.convertId);
    }

}
