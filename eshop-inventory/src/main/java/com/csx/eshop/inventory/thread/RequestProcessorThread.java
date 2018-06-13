package com.csx.eshop.inventory.thread;

import com.csx.eshop.inventory.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.thread
 * @Description: 执行请求的工作线程
 * @date 2018/5/21 0021
 */
public class RequestProcessorThread implements Callable<Boolean> {
    private static final Logger LOGGER=LoggerFactory.getLogger(RequestProcessorThread.class);
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true){
                //如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
                Request request=queue.take();
                LOGGER.info("===========日志===========: 工作线程处理请求，商品id=" + request.getProductId());
                request.process();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
