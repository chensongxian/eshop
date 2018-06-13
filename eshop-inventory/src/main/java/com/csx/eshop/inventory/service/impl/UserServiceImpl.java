package com.csx.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.csx.eshop.inventory.dao.RedisDAO;
import com.csx.eshop.inventory.mapper.UserMapper;
import com.csx.eshop.inventory.model.User;
import com.csx.eshop.inventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.service.impl
 * @Description: TODO
 * @date 2018/5/21 0021
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisDAO redisDAO;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {
        redisDAO.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");

        String userJSON = redisDAO.get("cached_user_lisi");
        JSONObject userJSONObject = JSONObject.parseObject(userJSON);

        User user = new User();
        user.setName(userJSONObject.getString("name"));
        user.setAge(userJSONObject.getInteger("age"));

        return user;
    }
}
