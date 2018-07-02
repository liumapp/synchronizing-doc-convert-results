package com.liumapp.demo.convert.sync.controller;

import com.liumapp.demo.convert.sync.socket.SocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumapp
 * @file SocketController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/2/18
 */
@RestController
@RequestMapping("socket")
public class SocketController {

    private static Logger logger = LoggerFactory.getLogger(SocketController.class);

    @RequestMapping(value = "/send")
    public String sendMsg(String path, String socketId) throws Exception {
        if (path == null) {
            throw new Exception("path must required");
        } else if (socketId == null) {
            throw new Exception("socketId must required");
        }

        logger.info("get path : " + path);
        SocketServer.sendMessage(path, socketId);
        return "success";
    }

}
