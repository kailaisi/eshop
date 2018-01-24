package com.kailaisi.eshop.request;

import com.kailaisi.eshop.model.ProductInventory;
import com.kailaisi.eshop.service.ProductInventoryService;

/**
 * 数据更新的请求
 * 先删除缓存，再更新数据库
 */
public class InventoryCntDBDUpdateRequest implements Request {

    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    public InventoryCntDBDUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //删除缓存
        productInventoryService.removeProductInventory(productInventory);
        //更新数据库
        productInventoryService.updateProductInventory(productInventory);
    }
}
