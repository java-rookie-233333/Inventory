package com.jorden.eshop.request;

import com.jorden.eshop.model.InventoryCnt;
import com.jorden.eshop.service.InventoryCntService;

/**
 * 商品库存db
 *
 */
public class ProductInventoryDBUpdateRequest implements Request {
	/**
	 * 商品库存
	 */
	private InventoryCnt inventory;
	/**
	 * 商品库存Service
	 */
	private InventoryCntService productInventoryService;

	public ProductInventoryDBUpdateRequest(InventoryCnt inventory, InventoryCntService productInventoryService) {
		super();
		this.inventory = inventory;
		this.productInventoryService = productInventoryService;
	}

	@Override
	public void execute() {
		/*修改库存*/
		productInventoryService.update(inventory);
		/*删除redis缓存*/
		productInventoryService.removeProductInventoryCache(inventory);
		
	}
	
	public Integer getProductId() {
		return inventory.getProductid();
	}
	
}
