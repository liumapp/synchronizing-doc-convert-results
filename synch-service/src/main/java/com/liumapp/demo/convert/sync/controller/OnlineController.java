package com.liumapp.demo.convert.sync.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumapp
 * @file OnlineController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/4/18
 */
@RestController
@RequestMapping("online")
public class OnlineController {

    @RequestMapping("/getnumber")
    public String getNumber () {

        return JSON.toJSONString("success");
    }

}
