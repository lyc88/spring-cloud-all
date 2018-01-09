package com.ssp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 记得写注释
 * @author: sunshaoping
 * @date: Create by in 下午6:19 2018/1/4
 */
@RestController

public class EdoEnvController {

    private final Environment environment;

    @Autowired
    public EdoEnvController(Environment environment) {this.environment = environment;}

    @GetMapping("/echo/env/{name}")
    public Object
    getEnv(@PathVariable("name") String name) {
        Map<String, String> data = new HashMap<>();
        data.put(name, environment.getProperty(name));
        return data;

    }

}
