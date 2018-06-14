package com.csx.eshop.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.csx.eshop.cache.ha.http.HttpClientUtils;
import com.csx.eshop.cache.ha.model.ProductInfo;
import com.netflix.hystrix.*;

/**
 * @author csx
 * @Package com.csx.eshop.cache.ha.hystrix.command
 * @Description: 获取商品信息
 * @date 2018/6/14 0014
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {
    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfoCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetProductInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withQueueSizeRejectionThreshold(10))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(30)
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        .withCircuitBreakerSleepWindowInMilliseconds(3000))
        );
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        System.out.println("调用接口，查询商品数据，productId=" + productId);

        if(productId.equals(-1L)) {
            throw new Exception();
        }

        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

//	@Override
//	protected String getCacheKey() {
//		return "product_info_" + productId;
//	}

    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("降级商品");
        return productInfo;
    }
}
