package com.kailaisi.eshop.service;

import com.kailaisi.eshop.request.Request;

/**
 * 异步请求操作处理，
 * 每一个请求过来，会将请求根据对应的id信息，将请求路由到对应的消息处理队列中。
 */
public interface RequestAsyncProcessService {
    void process(Request request);
}
