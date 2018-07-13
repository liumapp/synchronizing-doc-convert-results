package com.liumapp.demo.convert.sync.queue.pattern;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file AutoJobPattern.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/13/18
 */
@Component
public class AutoJobPattern {

    private String name;

    public AutoJobPattern() {
    }

    public AutoJobPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
