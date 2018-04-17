package com.ssp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunshaoping
 * @date: Create by in 下午2:18 2018/1/10
 */
@RefreshScope
@RestController
public class EchoController {


//    @Value("${my.name}")
    private String myName;

    @Value("${java.vendor}")
    private String javaVendor;

    @GetMapping("my-name")
    public String getMyName() {
        return myName;
    }

    @GetMapping("java-vendor")
    public String javaVendor(){
        return javaVendor;
    }
}
