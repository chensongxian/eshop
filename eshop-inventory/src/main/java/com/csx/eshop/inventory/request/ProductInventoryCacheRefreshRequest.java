package com.csx.eshop.inventory.request;

import com.csx.eshop.inventory.model.ProductInventory;
import com.csx.eshop.inventory.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.request
 * @Description: 重新加载商品库存的缓存
 * @date 2018/5/22 0022
 */
public class ProductInventoryCacheRefreshRequest implements Request{
    private static final Logger LOGGER=LoggerFactory.getLogger(ProductInventoryCacheRefreshRequest.class);
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    private boolean forceRefresh;

    public ProductInventoryCacheRefreshRequest(Integer productId,
                                               ProductInventoryService productInventoryService,
                                               boolean forceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRefresh=forceRefresh;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);

        LOGGER.info("===========日志===========: 已查询到商品最新的库存数量，商品id=" + productId + ", 商品库存数量=" + productInventory.getInventoryCnt());

        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    public boolean isForceRefresh() {
        return forceRefresh;
    }
}
