package com.ssp.feign;

import com.ssp.domain.User;

import java.util.Collection;

/**
 *  user Service
 * @author: sunshaoping
 * @date: Create by in 下午5:57 2018/1/10
 */
public interface UserFeign {

    /**
     * 保存 user
     * @param user
     * @return 保存成功返回user对象否则返回
     */
    User save(User user);

    /**
     * 查询所有user
     * @return 不会返回 <p>null</p>
     */
    Collection<User> findAll();
}
