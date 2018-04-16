package com.ssp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关 启动类
 * @author: sunshaoping
 * @date: Create by in 下午2:45 2018/1/23
 */

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ZuulApplication.class)
                .web(true)
                .run(args);
    }

}
