package com.xjjlearning.apache.dubbo.dubbosamplespringcloudprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/**
 * DubboComponentScan->DubboComponentScanRegistrar
 * ->getPackagesToScan():获取扫描路径
 * ->registerServiceAnnotationBeanPostProcessor():扫描@Service
 *   //dubbo实现了spring的beanPostProcessor(bean后置处理器), 名字叫做ServiceAnnotationBeanPostProcessor
 *   ->postProcessBeanDefinitionRegistry()->registerServiceBeans():注册@Service
 *     ->scanner.scan().doScan()   //Registers @Service Bean first
 *       ->postProcessBeanDefinition()  //后置处理bean定义(默认值)
 *       ->processCommonDefinitionAnnotations()
 *       ->registerBeanDefinition()
 *     ->findServiceBeanDefinitionHolders()  //
 *     ->registerServiceBean()  //注册bean
 *       ...
 *       ->buildServiceBeanDefinition()  //返回实际注册的bean
 *       ->registry.registerBeanDefinition()  //注册函数, 注入Spring IOC容器(concurrentHashMap), 类型为BeanDefinition
 *         ->this.beanDefinitionMap.put(beanName, beanDefinition)
 * ->registerReferenceAnnotationBeanPostProcessor()扫描@Reference
 */
@EnableDubbo(scanBasePackages = "com.xjjlearning.apache.dubbo.dubbosamplespringcloudprovider")
public class DubboSampleSprngCloudProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboSampleSprngCloudProviderApplication.class, args);
	}
}
