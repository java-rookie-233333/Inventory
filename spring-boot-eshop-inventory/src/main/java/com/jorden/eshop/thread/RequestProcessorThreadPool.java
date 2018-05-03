package com.jorden.eshop.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jorden.eshop.request.Request;
import com.jorden.eshop.request.RequestQueue;

/**
 * 初始化请求处理器
 * jorden.li
 */
public class RequestProcessorThreadPool {
	
	private ExecutorService executors=Executors.newFixedThreadPool(10);
	
	private RequestProcessorThreadPool() {
		for (int i = 0; i < 10; i++) {
			RequestQueue requestQueue=RequestQueue.init();
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
			requestQueue.addQueue(queue);
			executors.submit(new  RequestProcessorThread(queue));
			
			
			
		}
		
	}
	
	/**
	 * JVM机制 保证高并发只执行一次
	 *
	 */
	private static class Singleton{
		private static  RequestProcessorThreadPool pool;
		static {
			pool=new RequestProcessorThreadPool();
		}
		
		public static RequestProcessorThreadPool getInstance(){
			return pool;
		}
	}
	
	/**
	 * 初始化 
	 */
	public static void init(){
		Singleton.getInstance();
		
	}
	
	
	
	
	

}
