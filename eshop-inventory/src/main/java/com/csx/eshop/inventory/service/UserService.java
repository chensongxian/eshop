package com.csx.eshop.inventory.service;

import com.csx.eshop.inventory.model.User;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.service
 * @Description: TODO
 * @date 2018/5/21 0021
 */
public interface UserService {
    /**
     * 查询用户信息
     * @return 用户信息
     */
    public User findUserInfo();

    /**
     * 查询redis中缓存的用户信息
     * @return
     */
    public User getCachedUserInfo();
}
