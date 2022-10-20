package com.xjjlearning.springcloud.nacossamplespringcloudconfig;

import com.alibaba.cloud.nacos.refresh.NacosContextRefresher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.cloud.bootstrap.BootstrapApplicationListener;
import org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosSampleSpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSampleSpringCloudConfigApplication.class, args);
    }

}
