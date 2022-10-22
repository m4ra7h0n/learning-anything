package com.xjjlearning.alibaba.cloud.sentinel.cleaner;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.util.StringUtils;

//url资源清洗, /clean/下的所有接口走同一个qps监控
public class CustomerCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        if (StringUtils.isEmpty(originUrl)) {
            return originUrl;
        }
        if (originUrl.startsWith("/clean/")) {
            return "/clean/*";
        }
        return originUrl;
    }
}
