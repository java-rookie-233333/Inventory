package com.jorden.eshop.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "inventory_cnt")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCnt {
	/*商品id*/    
    @Id
    @Column(name = "productId")
    private Integer productid;
    /*商品库存数量*/
    @Column(name = "inventoryCnt")
    private Integer inventorycnt;

}