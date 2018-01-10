package com.ssp.controller;

import com.ssp.domain.User;
import com.ssp.feign.UserFeign;
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
public class UserRestApiController {

    private final UserFeign userFeign;

    @Autowired
    public UserRestApiController(UserFeign userFeign) {this.userFeign = userFeign;}

    @PostMapping("save")
    public User save(@RequestBody User user) {
        return userFeign.save(user);
    }


    @GetMapping("all")
    public Collection<User> findAll() {
        return userFeign.findAll();
    }
}
