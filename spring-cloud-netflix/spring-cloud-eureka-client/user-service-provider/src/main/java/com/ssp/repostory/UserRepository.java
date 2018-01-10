package com.ssp.repostory;

import com.ssp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * user 仓储
 * @author: sunshaoping
 * @date: Create by in 下午6:18 2018/1/10
 */
@Repository
public class UserRepository {

    private ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);


    public Collection<User> findAll() {
        return userMap.values();
    }

    public User save(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        return userMap.putIfAbsent(id, user) == null ? user : null;
    }
}
