package com.ssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * user 服务消费方 启动类
 * @author: sunshaoping
 * @date: Create by in 下午6:26 2018/1/10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceConsumerApplication {
    public static void main(String[] args) {

        SpringApplication.run(UserServiceConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
