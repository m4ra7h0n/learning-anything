package com.xjjlearning.alibaba.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class NacosConfigSourcecodeApplicationTests {

    @Test
    void contextLoads() throws NacosException, InterruptedException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "47.95.7.37:" + 8848);
        properties.put(PropertyKeyConst.CONFIG_LONG_POLL_TIMEOUT, "20000");
        properties.put(PropertyKeyConst.CONFIG_RETRY_TIME, "3000");
        properties.put(PropertyKeyConst.MAX_RETRY, "5");
        ConfigService configService = NacosFactory.createConfigService(properties);
        
        configService.addListener("test", "DEFAULT_GROUP", new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(configInfo);
            }
        });

        TimeUnit.SECONDS.sleep(100);
    }

}
