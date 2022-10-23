package com.xjjlearning.alibaba.cloud.sentinel.config;

import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    @Bean
    ConsumerConfig consumerConfig() {
        // dubbo的配置可以禁止使用sentinel
        ConsumerConfig consumerConfig = new ConsumerConfig();
//         Uncomment below line if you don't want to enable Sentinel for Dubbo service consumers.
//         consumerConfig.setFilter("-sentinel.dubbo.consumer.filter");
        return consumerConfig;
    }

}
