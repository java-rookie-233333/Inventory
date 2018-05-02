package com.jorden.eshop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jorden.eshop.thread.RequestProcessorThreadPool;

public class Initlistener  implements  ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		RequestProcessorThreadPool.init();
	}

}
