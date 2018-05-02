package com.jorden.eshop.request;

import com.jorden.eshop.model.InventoryCnt;
import com.jorden.eshop.service.InventoryCntService;

/**
 * 重新加载数据库中的库存
 * @author jorden.li
 *
 */
public class ProductInventoryCacheRefreshRequest  implements Request{
	
	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品库存Service
	 */
	private InventoryCntService productInventoryService;
	
	
	@Override
	public void execute() {
		//从数据库查询最新的商品库存
		InventoryCnt inventoryCnt=productInventoryService.findById(productId);
		// 将最新的商品库存写入缓存（redis）
		productInventoryService.setProductInventoryCache(inventoryCnt);
		
	}


	public ProductInventoryCacheRefreshRequest(Integer productId, InventoryCntService productInventoryService) {
		super();
		this.productId = productId;
		this.productInventoryService = productInventoryService;
	}
	

	/**
	 * 获取商品id
	 * @return
	 */
	public Integer getProductId(){
		return productId;
	}
	

}
