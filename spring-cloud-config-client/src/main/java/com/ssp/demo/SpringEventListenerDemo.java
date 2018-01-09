package com.ssp.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * TODO 记得写注释
 * @author: sunshaoping
 * @date: Create by in 下午4:11 2018/1/4
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();
        context.addApplicationListener((ApplicationListener<MyApplicationEvent>) applicationEvent -> System.out.println("事件源：" + applicationEvent.getSource()));
        context.refresh();
        context.publishEvent(new MyApplicationEvent("hello"));
        context.publishEvent(new MyApplicationEvent("你好"));
        context.publishEvent(new MyApplicationEvent("hello world"));

    }

    private static class MyApplicationEvent extends ApplicationEvent {


        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

}
