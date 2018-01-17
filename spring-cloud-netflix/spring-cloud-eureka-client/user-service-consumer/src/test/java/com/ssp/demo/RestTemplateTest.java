package com.ssp.demo;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate 测试
 * @author: sunshaoping
 * @date: Create by in 下午6:19 2018/1/15
 */
public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        Map map = restTemplate.getForEntity("http://localhost:8080/env", Map.class).getBody();
        System.out.println(map);
    }
}