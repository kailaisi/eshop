package com.kailaisi.eshop.request;

import com.kailaisi.eshop.model.ProductInventory;
import com.kailaisi.eshop.service.ProductInventoryService;

/**
 * 数据更新的请求
 * 先删除缓存，再更新数据库
 */
public class ProductInventoryDBDUpdateRequest implements Request {

    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBDUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
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

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
