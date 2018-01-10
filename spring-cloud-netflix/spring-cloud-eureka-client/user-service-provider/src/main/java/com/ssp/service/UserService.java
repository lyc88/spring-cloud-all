package com.ssp.service;

import com.ssp.domain.User;

import java.util.Collection;

/**
 * User Service
 * @author: sunshaoping
 * @date: Create by in 下午6:06 2018/1/10
 */
public interface UserService {

    User save(User user);

    Collection<User> findAll();
}
