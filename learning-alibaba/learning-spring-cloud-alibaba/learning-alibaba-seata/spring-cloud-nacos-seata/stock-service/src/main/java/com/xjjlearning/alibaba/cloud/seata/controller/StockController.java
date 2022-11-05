package com.xjjlearning.alibaba.cloud.seata.controller;

import com.xjjlearning.alibaba.cloud.seata.service.StockService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("stock")
public class StockController {
    @Resource
    private StockService stockService;
    @RequestMapping(value = "/deduct", method = RequestMethod.GET)
    public Boolean deduct(@PathParam("commodityCode") String commodityCode, @PathParam("count") Integer count){
        stockService.deduct(commodityCode, count);
        return true;
    }
}
