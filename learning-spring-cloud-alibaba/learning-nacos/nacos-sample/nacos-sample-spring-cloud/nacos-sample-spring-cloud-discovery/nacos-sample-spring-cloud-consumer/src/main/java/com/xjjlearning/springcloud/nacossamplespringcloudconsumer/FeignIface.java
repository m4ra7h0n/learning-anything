package com.xjjlearning.springcloud.nacossamplespringcloudconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "service-provider")
public interface FeignIface {
    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str);
}
