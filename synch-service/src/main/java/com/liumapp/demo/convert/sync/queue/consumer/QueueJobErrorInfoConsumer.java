package com.liumapp.demo.convert.sync.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.demo.convert.sync.config.ConvertConfig;
import com.liumapp.demo.convert.sync.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.convert.sync.queue.pattern.QueueJobErrorInfoPattern;
import com.liumapp.demo.convert.sync.socket.ConvertingResultSocketServer;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file ConvertDocResultService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/16/18
 */
@Service
public class QueueJobErrorInfoConsumer {

    public void handleError(String jsonObject) {
        QueueJobErrorInfoPattern queueJobErrorInfoPattern = JSON.parseObject(jsonObject, QueueJobErrorInfoPattern.class);
        if (queueJobErrorInfoPattern.getServiceName().equals(ConverterConsumer.class.toString())) {
            this.handleConvertError(queueJobErrorInfoPattern.getInfo());
        }
    }

    private void handleConvertError (String info) {
        ConvertDocPattern docPattern = JSON.parseObject(info, ConvertDocPattern.class);
        ConvertingResultSocketServer.sendStatusMessage(responseConvertJson(docPattern), docPattern.getConvertId());
    }

    private JSONObject responseConvertJson (ConvertDocPattern docPattern) {
        JSONObject object = new JSONObject();
        object.put("index", docPattern.getFileIndex());
        object.put("savename", docPattern.getSaveName());
        object.put("status", ConvertConfig.ConvertStatus.CONVERTED_FAILD);
        return object;
    }

}
