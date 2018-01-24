package com.kailaisi.eshop.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求的内存队列
 */
public class RequestQueue {
    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    public Integer getSize() {
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(int index) {
        return queues.get(index);
    }

    private static class Singleton {
        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance() {
            return instance;
        }
    }

    /**
     * jvm的机制保证多线程的并发安全，多少个线程并发，都只执行一次。
     *
     * @return
     */
    public static RequestQueue getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 添加一个队列
     *
     * @param queue
     */
    public void addQueue(ArrayBlockingQueue<Request> queue) {
        queues.add(queue);
    }

}
