package com.kailaisi.eshop.thread;

import com.kailaisi.eshop.request.Request;
import com.kailaisi.eshop.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求处理的线程池单例
 */
public class RequestProcessorThreadPool {
    /**
     * 初始化线程池
     */
    private ExecutorService threadPool=Executors.newFixedThreadPool(10);
    RequestQueue requestQueue=RequestQueue.getInstance();
    private RequestProcessorThreadPool(){
        for(int i=0;i<10;i++){
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

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
     * jvm的机制保证多线程的并发安全，多少个线程并发，都只执行一次。
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    /**
     * 初始化便捷方法
     */
    public static void init(){
        getInstance();
    }
}
