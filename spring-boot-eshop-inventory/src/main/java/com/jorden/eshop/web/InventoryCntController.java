package com.jorden.eshop.web;
import com.jorden.eshop.core.Result;
import com.jorden.eshop.core.ResultGenerator;
import com.jorden.eshop.model.InventoryCnt;
import com.jorden.eshop.request.ProductInventoryCacheRefreshRequest;
import com.jorden.eshop.request.ProductInventoryDBUpdateRequest;
import com.jorden.eshop.request.Request;
import com.jorden.eshop.service.InventoryCntService;
import com.jorden.eshop.service.RequestAsyncProcessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by jorden.li on 2018/05/01.
*/
@RestController
@RequestMapping("/inventory/cnt")
public class InventoryCntController {
    @Resource
    private InventoryCntService inventoryCntService;
    
    @Autowired 
    RequestAsyncProcessService requestAsyncProcessService;
    
    
    @PostMapping("/add")
    public Result add(InventoryCnt inventoryCnt) {
        inventoryCntService.save(inventoryCnt);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        inventoryCntService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(InventoryCnt inventoryCnt) {
        inventoryCntService.update(inventoryCnt);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        InventoryCnt inventoryCnt = inventoryCntService.findById(id);
        return ResultGenerator.genSuccessResult(inventoryCnt);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InventoryCnt> list = inventoryCntService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    /**
     * 更新商品库存
     * @return
     */
    @RequestMapping("/updateProductInventoryCacheRefreshRequest")
    public Result updateProductInventoryCacheRefreshRequest(@RequestBody InventoryCnt inventoryCnt){
    	/**更新库存*/
    	Request request=new ProductInventoryDBUpdateRequest(inventoryCnt, inventoryCntService);
    	requestAsyncProcessService.process(request);;
    	return ResultGenerator.genSuccessResult();
    }
}
