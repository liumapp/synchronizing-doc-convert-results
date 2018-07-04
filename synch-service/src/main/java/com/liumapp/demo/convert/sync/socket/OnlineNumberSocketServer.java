package com.liumapp.demo.convert.sync.socket;

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

    private static int onlineNumber = 0;

    private static CopyOnWriteArraySet<OnlineNumberSocketServer> clientWebSet = new CopyOnWriteArraySet<OnlineNumberSocketServer>();

    private Session session;

    @OnOpen
    public void onOpen (Session session) {
        this.session = session;
        clientWebSet.add(this);
        addOnlineNumber();
    }

    @OnClose
    public void onClose () {
        clientWebSet.remove(this);
        subOnlineNumber();
    }

    @OnMessage
    public void onMessage (String msg, Session session) {
        for (OnlineNumberSocketServer client: clientWebSet) {
            this.session.getAsyncRemote().sendText("hello from server");
        }
    }

    public synchronized int addOnlineNumber () {
        return OnlineNumberSocketServer.onlineNumber++;
    }

    public synchronized int subOnlineNumber () {
        return OnlineNumberSocketServer.onlineNumber--;
    }


}
