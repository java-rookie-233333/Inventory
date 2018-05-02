package com.jorden.eshop.service;
import com.jorden.eshop.model.InventoryCnt;
import com.jorden.eshop.core.Service;


/**
 * Created by jorden.li on 2018/05/01.
 */
public interface InventoryCntService extends Service<InventoryCnt> {
	/*将最新的商品库存数量，刷新到redis缓存中去*/
	void setProductInventoryCache(InventoryCnt inventoryCnt);
	void removeProductInventoryCache(InventoryCnt inventoryCnt);

}
