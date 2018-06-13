package com.csx.eshop.inventory.service.impl;

import com.csx.eshop.inventory.dao.RedisDAO;
import com.csx.eshop.inventory.mapper.ProductInventoryMapper;
import com.csx.eshop.inventory.model.ProductInventory;
import com.csx.eshop.inventory.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.service.impl
 * @Description: 商品库存service
 * @date 2018/5/22 0022
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {
    private static final Logger LOGGER=LoggerFactory.getLogger(ProductInventoryServiceImpl.class);
    @Resource
    private ProductInventoryMapper productInventoryMapper;
    @Resource
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
        LOGGER.info("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.delete(key);
        LOGGER.info("\"===========日志===========: 已删除redis中的缓存，key=\" + key");
    }

    /**
     * 根据商品id查询商品库存
     * @param productId 商品id
     * @return 商品库存
     */
    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
        LOGGER.info("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);
    }

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;

        String key = "product:inventory:" + productId;
        String result = redisDAO.get(key);

        if(result != null && !"".equals(result)) {
            try {
                inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
