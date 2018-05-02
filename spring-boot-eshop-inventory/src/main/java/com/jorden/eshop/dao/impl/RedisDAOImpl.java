package com.jorden.eshop.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorden.eshop.dao.RedisDAO;

import redis.clients.jedis.Jedis;

/**
 *  @author jorden,.li
 *  redisdao
 *
 */
@Service("redisDao")
public class RedisDAOImpl implements RedisDAO {
	/**redis 集群*/
//	@Autowired
//	private JedisCluster jedisCluster;
	
	@Autowired
	Jedis jedis;
	
	@Override
	public void set(String key, String value) {
		jedis.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedis.get(key);
	}

	@Override
	public void remove(String key) {
		jedis.del(key);
	}

}
