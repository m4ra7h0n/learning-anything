/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.xjjlearning.alibaba.cloud.seata.service;

import com.xjjlearning.alibaba.cloud.seata.entity.Order;
import com.xjjlearning.alibaba.cloud.seata.feign.StockFeign;
import com.xjjlearning.alibaba.cloud.seata.repository.OrderDAO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Program Name: springcloud-nacos-seata
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/8/28 4:05 PM
 */
@Service
public class OrderService {
    @Resource
    StockFeign stockFeign;

    @Resource
    OrderDAO orderDAO;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5)); //一个5块钱
        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(orderMoney);
        orderDAO.insert(order);
        stockFeign.deduct(commodityCode, count);
    }
}
