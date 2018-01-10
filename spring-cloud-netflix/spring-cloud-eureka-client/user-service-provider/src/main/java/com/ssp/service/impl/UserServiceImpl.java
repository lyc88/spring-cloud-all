package com.ssp.service.impl;

import com.ssp.domain.User;
import com.ssp.repostory.UserRepository;
import com.ssp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * user Service 实现
 * @author: sunshaoping
 * @date: Create by in 下午6:16 2018/1/10
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
