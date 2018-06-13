package com.csx.eshop.cache.spring;

import org.springframework.context.ApplicationContext;

/**
 * @author csx
 * @Package com.csx.eshop.cache.spring
 * @Description: TODO
 * @date 2018/5/22 0022
 */
public class SpringContext {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
