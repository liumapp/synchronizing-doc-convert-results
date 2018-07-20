package com.liumapp.demo.convert.sync.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.demo.convert.sync.config.ConvertConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author liumapp
 * @file ConvertingResultSocketServer.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/5/18
 */
@ServerEndpoint(value = "/convertingSocket/{convertId}")
@Component
public class ConvertingResultSocketServer {

    private static Logger logger = LoggerFactory.getLogger(ConvertingResultSocketServer.class);

    private static ConcurrentHashMap<Integer, ConvertingResultSocketServer> convertHashMap = new ConcurrentHashMap<Integer, ConvertingResultSocketServer>();

    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    private Session session;

    private Integer convertId;

    @OnOpen
    public void onOpen (Session session, @PathParam(value = "convertId") Integer convertId) {
        this.session = session;
        this.convertId = convertId;
        convertHashMap.put(convertId, this);
        sessionPool.put(convertId.toString(), session);
        logger.info("new  convert job in , the convertId is :" + convertId.toString());
    }

    @OnClose
    public void onClose () {
        Integer convertId = this.convertId;
        sessionPool.remove(convertId.toString());
        convertHashMap.remove(convertId);
        logger.info("convert job out , the convertId is :" + convertId.toString());
    }

    @OnMessage
    public void onMessage (String msg) {
        logger.info("convert info: " + msg + ", the convertId is : " + this.convertId.toString());
    }

    public static void sendAll (String msg) {
        for (Integer convertId: convertHashMap.keySet()) {
            ConvertingResultSocketServer client = convertHashMap.get(convertId);
            try {
                client.session.getAsyncRemote().sendText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage (String msg, Integer convertId) {
        logger.info("convert result done , send msg : " + msg);
        Session session = null;

        if (sessionPool.size() > 0 && convertId != null) {
            session = sessionPool.get(convertId.toString());
        }

        if(session != null) {
            try {
                session.getAsyncRemote().sendText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendStatusMessage (JSONObject msg, Integer convertId) {
        Integer status = msg.getInteger("status");
        Session session = null;
        if (status == ConvertConfig.ConvertStatus.CONVERTED_SUCCESS) {
            logger.info("convert success , send msg to frontend : " + msg);
        } else if (status == ConvertConfig.ConvertStatus.CONVERTED_FAILD) {
            logger.error("convert failed , send msg to frontend : " + msg);
        }

        if (sessionPool.size() > 0 && convertId != null) {
            session = sessionPool.get(convertId.toString());
        }

        if (session != null) {
            try {
                session.getAsyncRemote().sendText(JSON.toJSONString(msg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
