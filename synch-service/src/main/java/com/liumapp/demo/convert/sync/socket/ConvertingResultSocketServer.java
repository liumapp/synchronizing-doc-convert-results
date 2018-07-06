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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    private static Map<Integer, Session> sessionPool = new HashMap<Integer, Session>();

    private Session session;

    private Integer convertId;

    @OnOpen
    public void onOpen (Session session, @PathParam(value = "convertId") Integer convertId) {
        this.session = session;
        this.convertId = convertId;
        resultWebSet.add(this);
        sessionPool.put(convertId, session);
        logger.info("new  convert job in , the convertId is :" + convertId.toString());
    }

    @OnClose
    public void onClose () {
        Integer convertId = this.convertId;
        sessionPool.remove(convertId);
        resultWebSet.remove(this);
        logger.info("convert job out , the convertId is :" + convertId.toString());
    }

    @OnMessage
    public void onMessage (String msg) {
        logger.info("convert info: " + msg + ", the convertId is : " + this.convertId.toString());
    }

    public void sendAll (String msg) {
        for (ConvertingResultSocketServer client: resultWebSet) {
            client.session.getAsyncRemote().sendText(msg);
        }
    }

    public void sendMessage (String msg, Integer convertId) {
        Session session = sessionPool.get(convertId);
        if(session != null){
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
