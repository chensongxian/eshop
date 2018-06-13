package com.csx.eshop.inventory.thread;

import com.csx.eshop.inventory.request.Request;
import com.csx.eshop.inventory.request.RequestQueue;

import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.thread
 * @Description: 请求处理线程池，单例
 * @date 2018/5/21 0021
 */
public class RequestProcessorThreadPool {
    /**
     * 线程池
     */
    private ExecutorService threadPool=Executors.newFixedThreadPool(10);

    public RequestProcessorThreadPool() {
        RequestQueue requestQueue=RequestQueue.getInstance();
        for(int i=0;i<10;i++){
            ArrayBlockingQueue<Request> queue=new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     * 静态内部类单例模式
     */
    private static class Singleton{
        private static RequestProcessorThreadPool instance;

        static {
            instance=new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance(){
            return instance;
        }
    }

    /**
     * 获取实例
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    /**
     * 便捷初始化
     */
    public static void init(){
        getInstance();
    }
}
