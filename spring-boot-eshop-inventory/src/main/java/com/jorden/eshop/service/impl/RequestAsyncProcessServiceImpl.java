package com.jorden.eshop.service.impl;

import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.stereotype.Service;

import com.jorden.eshop.request.Request;
import com.jorden.eshop.request.RequestQueue;
import com.jorden.eshop.service.RequestAsyncProcessService;

/**
 * 
 * 异步请求处理
 * 
 * @author jorden.li
 *
 */
@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService{

	@Override
	public void process(Request request) {
		
		try {
			ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
			queue.put(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	/**
	 * 获取路由到的内存队列
	 * @param productId 商品id
	 * @return 内存队列
	 */
	private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
		RequestQueue requestQueue = RequestQueue.init();
		
		// 先获取productId的hash值
		String key = String.valueOf(productId);
		int h;
		int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		
		// 对hash值取模，将hash值路由到指定的内存队列中，比如内存队列大小8
		// 用内存队列的数量对hash值取模之后，结果一定是在0~7之间
		// 所以任何一个商品id都会被固定路由到同样的一个内存队列中去的
		int index = (requestQueue.getQueueSize() - 1) & hash;
		
		return requestQueue.getQueue(index);
	}
	



}
