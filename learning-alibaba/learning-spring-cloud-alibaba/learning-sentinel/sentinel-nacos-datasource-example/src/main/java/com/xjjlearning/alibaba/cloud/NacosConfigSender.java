package com.xjjlearning.alibaba.cloud;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

public class NacosConfigSender {
    public static void main(String[] args) throws Exception{
        final String remoteAddress = "localhost:8848";
        final String groupId = "Sentinel_Demo";
        final String dataId = "com.xjjlearning.alibaba.cloud.sentinel.demo.flow.rule";
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"/say\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 1,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";
        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}
