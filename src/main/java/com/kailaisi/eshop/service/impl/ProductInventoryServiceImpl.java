package com.kailaisi.eshop.service.impl;

import com.kailaisi.eshop.dao.RedisDao;
import com.kailaisi.eshop.mapper.ProductInVentoryMapper;
import com.kailaisi.eshop.model.ProductInventory;
import com.kailaisi.eshop.service.ProductInventoryService;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {
    @Resource
    private ProductInVentoryMapper productInVentoryMapper;
    @Resource
    private RedisDao redisDao;

    @Override
    public void updateProductInventory(ProductInventory inventory) {
        productInVentoryMapper.updateProductInventory(inventory);
    }

    @Override
    public void removeProductInventory(ProductInventory inventory) {
        String key = "product:inventory:" + inventory.getProductId();
        redisDao.delete(key);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInVentoryMapper.findProductInventory(productId);
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long cnt=0L;
        String key = "product:inventory:" + productId;
        String result = redisDao.get(key);
        if(result!=null&& !result.equals("")){
            try {
                cnt=Long.parseLong(result);
                return new ProductInventory(productId,cnt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.set(key,String.valueOf(productInventory.getInventoryCnt()));
    }
}
