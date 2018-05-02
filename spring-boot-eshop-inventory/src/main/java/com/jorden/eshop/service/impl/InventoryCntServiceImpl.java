package com.jorden.eshop.service.impl;

import com.jorden.eshop.dao.InventoryCntMapper;
import com.jorden.eshop.dao.RedisDAO;
import com.jorden.eshop.model.InventoryCnt;
import com.jorden.eshop.service.InventoryCntService;


import com.jorden.eshop.core.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.jorden.eshop.core.ProjectConstant.*;

/**
 * Created by jorden.li on 2018/05/01.
 */
@Service
@Transactional
public class InventoryCntServiceImpl extends AbstractService<InventoryCnt> implements InventoryCntService {
    @Resource
    private InventoryCntMapper inventoryCntMapper;
    
    @Autowired
    RedisDAO redisDao;

	@Override
	public void setProductInventoryCache(InventoryCnt inventoryCnt) {
		redisDao.set(REDIS_KEY+inventoryCnt.getProductid(), inventoryCnt.getInventorycnt()+"");
		
	}

	@Override
	public void removeProductInventoryCache(InventoryCnt inventoryCnt) {
		redisDao.remove(REDIS_KEY+inventoryCnt.getProductid());
		
	}

}
