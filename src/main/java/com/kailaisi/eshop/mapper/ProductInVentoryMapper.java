package com.kailaisi.eshop.mapper;

import com.kailaisi.eshop.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * 库存数据Dao
 */
public interface ProductInVentoryMapper {
    /**
     * 更新库存数量
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 查询库存数据
     *
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
