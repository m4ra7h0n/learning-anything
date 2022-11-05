package com.xjjlearning.alibaba.cloud.seata.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "stock-service")
public interface StockFeign {
    @GetMapping(value = "stock/deduct")
    public Boolean deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
