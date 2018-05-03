package com.jorden.eshop.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

import com.jorden.eshop.request.Request;

/**
 * 
 * 请求处理器
 * @author jorden.li
 *
 */
public class RequestProcessorThread  implements Callable<Boolean>{

	
	private ArrayBlockingQueue<Request> arrayBlockingQueue;
	
	public RequestProcessorThread(ArrayBlockingQueue<Request> arrayBlockingQueue) {
		this.arrayBlockingQueue = arrayBlockingQueue;
	}
	@Override
	public Boolean call() throws Exception {
		try {
			while (true) {
				Request request = arrayBlockingQueue.take();
				request.execute();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
}
