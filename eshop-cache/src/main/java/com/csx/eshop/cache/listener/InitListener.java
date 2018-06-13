package com.csx.eshop.cache.listener;

import com.csx.eshop.cache.kafka.KafkaConsumer;
import com.csx.eshop.cache.rebuild.RebuildCacheThread;
import com.csx.eshop.cache.spring.SpringContext;
import com.csx.eshop.cache.zk.ZooKeeperSession;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import scala.reflect.generic.Trees;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author csx
 * @Package com.csx.eshop.cache.listener
 * @Description: TODO
 * @date 2018/5/22 0022
 */
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        SpringContext.setApplicationContext(context);

        new Thread(new KafkaConsumer("cache-message")).start();
        new Thread(new RebuildCacheThread()).start();
        ZooKeeperSession.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
