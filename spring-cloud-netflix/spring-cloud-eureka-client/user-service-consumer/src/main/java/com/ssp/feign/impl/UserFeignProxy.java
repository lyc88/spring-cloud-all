package com.ssp.feign.impl;

import com.netflix.discovery.converters.Auto;
import com.ssp.domain.User;
import com.ssp.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * UserFeign 代理，调用远程接口
 * @author: sunshaoping
 * @date: Create by in 下午6:32 2018/1/10
 */
@Service
public class UserFeignProxy implements UserFeign {

    public static final String PROVIDER_SERVICE_URL = "http://user-service-provider/users";

    private final RestTemplate restTemplate;

    @Autowired
    public UserFeignProxy(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @Override
    public User save(User user) {
        return restTemplate.postForEntity(PROVIDER_SERVICE_URL, user, User.class).getBody();
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForEntity(PROVIDER_SERVICE_URL,Collection.class).getBody();
    }
}
