package com.kailaisi.eshop.control;

import com.kailaisi.eshop.model.ProductInventory;
import com.kailaisi.eshop.request.ProductInventoryCacheReloadRequest;
import com.kailaisi.eshop.request.ProductInventoryDBDUpdateRequest;
import com.kailaisi.eshop.request.Request;
import com.kailaisi.eshop.service.ProductInventoryService;
import com.kailaisi.eshop.service.RequestAsyncProcessService;
import com.kailaisi.eshop.vo.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ProductInventoryControl {
    @Resource
    private ProductInventoryService productInventoryService;
    @Resource
    private RequestAsyncProcessService requestAsyncProcessService;

    /**
     * 更新商品库存
     *
     * @param inventory
     * @return
     */
    @RequestMapping("/updateProductInventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventory inventory) {
        Response response;
        try {
            Request request = new ProductInventoryDBDUpdateRequest(inventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }

    /**
     * 更新商品库存
     *
     * @param productId
     * @return
     */
    @RequestMapping("/getProductInventory")
    @ResponseBody
    public ProductInventory getProductInventory(Integer productId) {
        ProductInventory response;
        try {
            Request request = new ProductInventoryCacheReloadRequest(productId, productInventoryService);
            requestAsyncProcessService.process(request);
            long start = System.currentTimeMillis();
            long waitTime = 0L;
            long endTime = 0L;
            //尝试等待前面的商品库存更新的操作
            while (true) {
                ProductInventory inventoryCache = productInventoryService.getProductInventoryCache(productId);
                if (inventoryCache != null) {//缓存有数据，直接返回
                    return inventoryCache;
                } else {//等待200ms，如果没有数据
                    Thread.sleep(20);
                    waitTime = System.currentTimeMillis() - start;
                    if (waitTime > 200) {
                        break;
                    }
                }
            }
            //尝试从数据库查询
            ProductInventory productInventory = productInventoryService.findProductInventory(productId);
            if (productInventory != null) {
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductInventory(productId, -1L);
    }
}
