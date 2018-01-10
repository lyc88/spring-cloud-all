package com.ssp.controller;

import com.ssp.domain.User;
import com.ssp.feign.UserFeign;
import com.ssp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * user 服务消费接口
 * @author: sunshaoping
 * @date: Create by in 下午6:01 2018/1/10
 */
@RequestMapping("/users")
@RestController
public class UserController implements UserFeign {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @Override
    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }


    @Override
    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }
}
