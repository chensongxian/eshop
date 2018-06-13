package com.csx.eshop.inventory.service;

import com.csx.eshop.inventory.request.Request;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.service
 * @Description: 请求异步执行的service
 * @date 2018/5/22 0022
 */
public interface RequestAsyncProcessService {
    void process(Request request);
}
