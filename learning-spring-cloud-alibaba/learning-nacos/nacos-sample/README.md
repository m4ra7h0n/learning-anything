# nacos  
## nacos-discovery  
这里的nacos-discovery就相当于dubbo, 在分布式环境中作为注册中心, 以及rpc调用  
**1.服务注册**  
源码思路:  
maven依赖入手: spring-cloud-starter-alibaba-nacos-discovery(springcloud插件)
找到pom后定位到spring-cloud-alibaba-nacos-discovery.jar找里面的spring.factories
找到NacosDiscoveryAutoConfiguration
三个核心bean:  
1.NacosServiceRegistry
2.NacosRegistration
3.NacosAutoServiceRegistration

NacosAutoServiceRegistration实现了服务向 Nacos 发起注册的功能，它继承自抽象类 AbstractAutoServiceRegistration
AbstractAutoServiceRegistration 中 bind()方法有一个EventListener

springcloud提供服务注册: org.springframework.cloud.client.serviceregistry.ServiceRegistry  
其包下的spring.factory中找到AutoServiceRegistrationAutoConfiguration(自动装配)  
该类自动注入AutoServiceRegistration, 寻找注入的实例(idea左边绿色的叶子)  
找到org.springframework.cloud.alibaba.nacos.NacosDiscoveryAutoConfiguration.nacosAutoServiceRegistration()方法  
来到NacosAutoServiceRegistration类
**2.服务地址的获取**  
**3.服务地址变化的感知**   
## nacos-config  
配置中心  

# ribbon  
负载均衡  
# openfeign  
内部集成RestTemplate, 配置接口  