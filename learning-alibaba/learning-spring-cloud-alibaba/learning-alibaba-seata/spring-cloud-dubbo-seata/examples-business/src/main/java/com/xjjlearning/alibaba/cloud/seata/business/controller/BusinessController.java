package com.xjjlearning.alibaba.cloud.seata.business.controller;

import com.xjjlearning.alibaba.cloud.seata.business.service.BusinessServiceImpl;
import com.xjjlearning.alibaba.cloud.seata.common.dto.BusinessDTO;
import com.xjjlearning.alibaba.cloud.seata.common.response.ObjectResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController {
    @Resource
    BusinessServiceImpl businessService;

    @PostMapping("/buy")
    ObjectResponse buy(@RequestBody BusinessDTO businessDTO) {
        System.out.println(businessDTO);
        return businessService.handleBusiness(businessDTO);
    }
}
