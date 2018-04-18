package com.ssp.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ssp.config.Address;
import com.ssp.config.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunshaoping
 * @date: Create by in 下午2:18 2018/1/10
 */
@RestController
@EnableConfigurationProperties(Person.class)
@RequestMapping("echo2")
public class Echo2Controller {

    private final Person person;

    @Value("${name}")
    private String myName;
    @Value("${name.name1}")
    private String name1;

    @Value("${java.vendor}")
    private String javaVendor;
    private final Environment environment;

    @Autowired
    public Echo2Controller(Person person, Environment environment) {
        this.person = person;
        this.environment = environment;
    }


    @GetMapping("person")
    public Person person() {
        Person person = new Person();
        BeanUtils.copyProperties(this.person,person);
        return person;
    }

    @GetMapping("my-name")
    public String getMyName() {
        return myName;
    }

    @GetMapping("name1")
    public String name1() {
        return name1;
    }

    @GetMapping("/property")
    public String envName(String key) {
        String name = environment.getProperty(key);
        System.out.println(name);
        return "Hello ," + name;
    }

    @GetMapping("/env-name")
    public String envName() {
        String name = environment.getProperty("name");
        System.out.println(name);
        return "Hello," + name;
    }

    @GetMapping("java-vendor")
    public String javaVendor() {
        return javaVendor;
    }
}
