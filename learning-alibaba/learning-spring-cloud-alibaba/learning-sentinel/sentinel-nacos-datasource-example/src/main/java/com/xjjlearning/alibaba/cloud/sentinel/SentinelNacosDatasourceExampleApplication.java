package com.xjjlearning.alibaba.cloud.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class SentinelNacosDatasourceExampleApplication {

	private static final String KEY = "/say";
	private static final String remoteAddress = "localhost:8848";
	private static final String groupId = "Sentinel_Demo";
	private static final String dataId  = "com.xjjlearning.alibaba.cloud.sentinel.demo.flow.rule";
	private static boolean isDemoNamespace = false;
	private static final String NACOS_NAMESPACE_ID  = "${namespace}";

	public static void main(String[] args) {
		if (isDemoNamespace) {
			loadMyNamespaceRules();
		} else {
			loadRules();
		}
		FlowQpsRunner runner = new FlowQpsRunner(KEY, 1, 100);
		runner.simulateTraffic();
		runner.tick();
		SpringApplication.run(SentinelNacosDatasourceExampleApplication.class, args);
	}


	//将nacos数据注册到flowRule管理器中
	private static void loadRules() {
		ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
				source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
				}));
		FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
	}

	//相比较上面到方法增加namespace
	private static void loadMyNamespaceRules() {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, remoteAddress);
		properties.put(PropertyKeyConst.NAMESPACE, NACOS_NAMESPACE_ID);

		ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, groupId, dataId,
				source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
				}));
		FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
	}

}
