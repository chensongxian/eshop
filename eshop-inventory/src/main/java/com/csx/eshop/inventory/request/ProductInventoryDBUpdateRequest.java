package com.csx.eshop.inventory.request;

import com.csx.eshop.inventory.model.ProductInventory;
import com.csx.eshop.inventory.service.ProductInventoryService;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.request
 * @Description: data update request，数据更新请求
 * @date 2018/5/22 0022
 */
public class ProductInventoryDBUpdateRequest implements Request{
    /**
     * 商品库存
     */
    private ProductInventory productInventory;
    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
                                           ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 删除redis中的缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        // 修改数据库中的库存
        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
