package com.csx.eshop.inventory.request;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.request
 * @Description: TODO
 * @date 2018/5/21 0021
 */
public interface Request {
    void process();

    Integer getProductId();
}
