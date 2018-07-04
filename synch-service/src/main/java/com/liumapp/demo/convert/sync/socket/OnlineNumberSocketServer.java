package com.liumapp.demo.convert.sync.socket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
    public void onOpen (Session session) throws IOException {
        this.session = session;
        clientWebSet.add(this);
        addOnlineNumber();
        System.out.println("new man in , now has :" + getOnlineNumber());
        this.onMessage("add new account", session);
    }

    @OnClose
    public void onClose () throws IOException {
        clientWebSet.remove(this);
        subOnlineNumber();
        System.out.println("a man out , now has :" + getOnlineNumber());
        this.onMessage("sub new account", session);
    }

    @OnMessage
    public void onMessage (String msg, Session session) throws IOException {
        for (OnlineNumberSocketServer client: clientWebSet) {
            client.session.getAsyncRemote().sendText(Integer.toString(getOnlineNumber()));
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
