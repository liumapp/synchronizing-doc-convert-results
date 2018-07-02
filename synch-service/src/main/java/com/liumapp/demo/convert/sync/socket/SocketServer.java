package com.liumapp.demo.convert.sync.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liumapp
 * @file SockerServer.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/2/18
 */

@ServerEndpoint(value = "/socketServer/{socketId}")
@Component
public class SocketServer {

    private Session session;
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();
    private static Map<String,String> sessionIds = new HashMap<String,String>();

    /**
     * 用户连接时触发
     * @param session
     * @param socketId
     */
    @OnOpen
    public void open(Session session,@PathParam(value="socketId")String socketId){
        this.session = session;
        sessionPool.put(socketId, session);
        sessionIds.put(session.getId(), socketId);
    }

    /**
     * 收到信息时触发
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        System.out.println("当前发送人sessionid为"+session.getId()+"发送内容为"+message);
    }

    /**
     * 连接关闭触发
     */
    @OnClose
    public void onClose(){
        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
    }

    /**
     * 发生错误时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     *信息发送的方法
     * @param message
     * @param socketId
     */
    public static void sendMessage(String message,String socketId){
        Session s = sessionPool.get(socketId);
        if(s!=null){
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前连接数
     * @return
     */
    public static int getOnlineNum(){
        return sessionPool.size();
    }

    /**
     * 获取在线用户名以逗号隔开
     * @return
     */
    public static String getOnlineUsers(){
        StringBuffer users = new StringBuffer();
        for (String key : sessionIds.keySet()) {
            users.append(sessionIds.get(key)+",");
        }
        return users.toString();
    }

    /**
     * 信息群发
     * @param msg
     */
    public static void sendAll(String msg) {
        for (String key : sessionIds.keySet()) {
            sendMessage(msg, sessionIds.get(key));
        }
    }
}
