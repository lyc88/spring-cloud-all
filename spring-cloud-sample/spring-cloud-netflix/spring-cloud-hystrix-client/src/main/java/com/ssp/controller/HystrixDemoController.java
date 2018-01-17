package com.ssp.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Hystrix demo
 * @author: sunshaoping
 * @date: Create by in 下午4:05 2018/1/16
 */
@RestController
public class HystrixDemoController {

    private final Random random = new Random();

    @GetMapping("hello-world-2")
    public String helloWorld2() {
        return new HelloWorldCommand().execute();
    }

    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {


        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("helloWorld"), 100);
        }

        @Override
        protected String run() throws Exception {
            return helloWorld();
        }

        @Override
        protected String getFallback() {
            return errorContent();
        }
    }

    @HystrixCommand(
            fallbackMethod = "errorContent",
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100"
                    )
            }
    )
    @GetMapping("hello-world")
    public String helloWorld() throws Exception {
        int i = random.nextInt(200);
        System.out.println("睡了" + i + "毫秒");
        TimeUnit.MILLISECONDS.sleep(i);
        return "hello world ";
    }

    public String errorContent() {

        return "error content";
    }
}
