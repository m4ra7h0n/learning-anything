package com.xjjlearning.alibaba.cloud.seata.controller;

import com.xjjlearning.alibaba.cloud.seata.repository.OrderDAO;
import com.xjjlearning.alibaba.cloud.seata.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("order")
@RestController //无语 忘加了
public class OrderController {
    @Resource
    private OrderService orderService;
    @RequestMapping("/placeOrder/commit")
    public Boolean placeOrderCommit() {
        orderService.placeOrder("1", "product-1", 1);
        return true;
    }
    @RequestMapping("/placeOrder/rollback")
    public Boolean placeOrderRollback() {
        orderService.placeOrder("1", "product-2", 1);
        return true;
    }
    @RequestMapping("/placeOrder")
    public Boolean placeOrder(String userId, String commodityCode, Integer count) {
        orderService.placeOrder(userId, commodityCode, count);
        return true;
    }
}
