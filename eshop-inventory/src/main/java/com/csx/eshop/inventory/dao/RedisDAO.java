package com.csx.eshop.inventory.dao;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.dao
 * @Description: TODO
 * @date 2018/5/21 0021
 */
public interface RedisDAO {
    void set(String key, String value);

    String get(String key);

    void delete(String key);
}
