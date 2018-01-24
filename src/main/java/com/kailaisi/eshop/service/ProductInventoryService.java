package com.kailaisi.eshop.service;

import com.kailaisi.eshop.model.ProductInventory;

public interface ProductInventoryService {
    /**
     * 更新商品库存
     *
     * @param inventory
     */
    void updateProductInventory(ProductInventory inventory);

    /**
     * 删除商品库存的缓存
     *
     * @param inventory
     */
    void removeProductInventory(ProductInventory inventory);

    /**
     * 根据商品库存id查询商品信息
     *
     * @param productId
     */
    ProductInventory findProductInventory(Integer productId);

    /**
     * 设置商品的库存缓存
     *
     * @param productInventory
     */
    void setProductInventoryCache(ProductInventory productInventory);
}
