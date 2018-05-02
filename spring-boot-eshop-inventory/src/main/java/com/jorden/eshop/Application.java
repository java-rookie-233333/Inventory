package com.jorden.eshop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jorden.eshop.listener.Initlistener;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
/**
 * 
 * @author 程序启动类
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public JedisCluster JedisClusterFactory() {
    	Set<HostAndPort> jedisClusterNodes =new HashSet<>();
    	jedisClusterNodes.add(new HostAndPort("192.168.31.19", 7003));
    	jedisClusterNodes.add(new HostAndPort("192.168.31.19", 7003));
    	jedisClusterNodes.add(new HostAndPort("192.168.31.19", 7003));
    	JedisCluster cluster=new JedisCluster(jedisClusterNodes);
		return cluster;
	}
    
    /**
     * 本地环境，redis无集群环境
     * @return
     */
    @Bean
    public Jedis  JedisFactory(){
    	Jedis jedis = null;
    	try {
    		jedis=new Jedis("127.0.0.1", 6379);
			
		} catch (Exception e) {
			e.printStackTrace();
    }
    	return jedis;
    }
    /**
     * 注册监听器
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
    	ServletListenerRegistrationBean servletListenerRegistrationBean = 
    			new ServletListenerRegistrationBean();
    	servletListenerRegistrationBean.setListener(new Initlistener());  
    	return servletListenerRegistrationBean;
    }

}

