package com.csx.eshop.inventory.dao.impl;

import com.csx.eshop.inventory.dao.RedisDAO;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.dao.impl
 * @Description: TODO
 * @date 2018/5/21 0021
 */
@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {
    @Resource
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void delete(String key) {
        jedisCluster.del(key);
    }
}
