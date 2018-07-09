package com.ssp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * eureka server
 * @author: sunshaoping
 * @date: Create by in 下午5:31 2018/1/10
 */
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixClientApplication.class, args);
    }
}
