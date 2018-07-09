package com.liumapp.demo.convert.sync.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author liumapp
 * @file OnlineNumberSocketServer.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/4/18
 */
@ServerEndpoint(value = "/onlineSocket")
@Component
public class OnlineNumberSocketServer {

    private static Logger logger = LoggerFactory.getLogger(OnlineNumberSocketServer.class);

    private static int onlineNumber = 0;

    private static CopyOnWriteArraySet<OnlineNumberSocketServer> clientWebSet = new CopyOnWriteArraySet<OnlineNumberSocketServer>();

    private Session session;

    @OnOpen
    public void onOpen (Session session) {
        this.session = session;
        clientWebSet.add(this);
        addOnlineNumber();
        logger.info("new man in , now has :" + getOnlineNumber());
        this.infoAllClient();
    }

    @OnClose
    public void onClose () {
        clientWebSet.remove(this);
        subOnlineNumber();
        logger.info("a man out , now has :" + getOnlineNumber());
        this.infoAllClient();
    }

    @OnMessage
    public void onMessage (String msg, Session session) {
        logger.info("session id : " + session.getId() + " has send msg: " + msg);
    }

    public void infoAllClient () {
        for (OnlineNumberSocketServer client: clientWebSet) {
            try {
                client.session.getAsyncRemote().sendText(Integer.toString(getOnlineNumber()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int getOnlineNumber () {
        return OnlineNumberSocketServer.onlineNumber;
    }

    public synchronized int addOnlineNumber () {
        return OnlineNumberSocketServer.onlineNumber++;
    }

    public synchronized int subOnlineNumber () {
        return OnlineNumberSocketServer.onlineNumber--;
    }

}
