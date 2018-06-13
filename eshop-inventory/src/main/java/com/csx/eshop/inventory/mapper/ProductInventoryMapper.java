package com.csx.eshop.inventory.mapper;

import com.csx.eshop.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.mapper
 * @Description: 库存数量mapper
 * @date 2018/5/21 0021
 */
public interface ProductInventoryMapper {
    /**
     * 更新库存数量
     * @param productInventory 商品库存
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 根据商品id查询商品库存信息
     * @param productId 商品id
     * @return 商品库存信息
     */
    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
