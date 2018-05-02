package com.jorden.eshop.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestQueue {
	
	private List<ArrayBlockingQueue<Request>> queues=new ArrayList<ArrayBlockingQueue<Request>>();

	private RequestQueue() {
		// TODO Auto-generated constructor stub
	}
	
	
	private static class Singteton{
		private static RequestQueue requestQueue;
		static {
			requestQueue=new RequestQueue();
		}
		public static RequestQueue getInstance(){
			return  requestQueue;
		}
	}
	
	public static  RequestQueue init(){
		 return Singteton.getInstance();
	}
	
	/**
	 * 添加一个内存队列
	 * @param arrayBlockingQueue
	 */
	public  void addQueue(ArrayBlockingQueue<Request> arrayBlockingQueue){
		this.queues.add(arrayBlockingQueue);
		
	}
	
	public int getQueueSize(){
		 return this.queues.size();
	}
	public ArrayBlockingQueue<Request> getQueue(int index){
		return this.queues.get(index);
	}
	
}
