package com.kailaisi.eshop.request;

import com.kailaisi.eshop.model.ProductInventory;
import com.kailaisi.eshop.service.ProductInventoryService;

/**
 * 重新加载商品库存的缓存
 */
public class ProductInventoryCacheReloadRequest implements Request {

    private Integer productId;
    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheReloadRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //从数据库中查询最新的库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        //将库存数量刷新到缓存中
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
