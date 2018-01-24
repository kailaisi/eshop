package com.kailaisi.eshop.thread;

import com.kailaisi.eshop.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 */
public class RequestProcessorThread implements Callable<Boolean> {
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
                //如果队列满了，就会阻塞
                Request take = queue.take();
                take.process();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
