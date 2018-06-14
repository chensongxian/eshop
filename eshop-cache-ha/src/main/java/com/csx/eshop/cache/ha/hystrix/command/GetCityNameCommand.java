package com.csx.eshop.cache.ha.hystrix.command;

import com.csx.eshop.cache.ha.cache.local.LocationCache;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

/**
 * @author csx
 * @Package com.csx.eshop.cache.ha.hystrix.command
 * @Description: 获取城市名称的command
 * @date 2018/6/14 0014
 */
public class GetCityNameCommand extends HystrixCommand<String> {
    private Long cityId;

    public GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("GetCityNameCommand"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetCityNamePool"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.getCityName(cityId);
    }
}
