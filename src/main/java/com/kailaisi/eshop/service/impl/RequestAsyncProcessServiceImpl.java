package com.kailaisi.eshop.service.impl;

import com.kailaisi.eshop.request.Request;
import com.kailaisi.eshop.request.RequestQueue;
import com.kailaisi.eshop.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
    @Override
    public void process(Request request) {
        //将请求路由到某一个队列中
        try {
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据商品的id，将这个请求路由到某一个具体的请求队列中去。
     *
     * @param productId
     * @return
     */
    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index = (requestQueue.getSize()) & hash;
        ArrayBlockingQueue<Request> queue = requestQueue.getQueue(index);
        return queue;
    }
}
