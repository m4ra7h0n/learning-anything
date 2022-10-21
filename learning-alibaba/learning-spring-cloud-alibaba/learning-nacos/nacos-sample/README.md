# 一. nacos-1.x

# nacos客户端  
这里的nacos-discovery就相当于dubbo, 在分布式环境中作为注册中心, 以及rpc调用  
**org.springframework.cloud:spring-cloud-starter-alibaba-nacos-discovery:0.2.2.RELEASE**
## 服务注册
该版本为孵化版本, 最新版artifactId为com.alibaba.cloud
源码思路:  
**思路1:**  
maven依赖入手: spring-cloud-starter-alibaba-nacos-discovery(springcloud插件)
找到pom后定位到spring-cloud-alibaba-nacos-discovery.jar找里面的spring.factories
找到NacosDiscoveryAutoConfiguration

**思路2:**  
springcloud提供服务注册: org.springframework.cloud.client.serviceregistry.ServiceRegistry  
其包下的spring.factory中找到AutoServiceRegistrationAutoConfiguration(自动装配)  
该类自动注入AutoServiceRegistration, 寻找注入的实例(idea左边绿色的叶子)  
找到org.springframework.cloud.alibaba.nacos.NacosDiscoveryAutoConfiguration

**思路汇总继续**:
三个核心bean:  
1.NacosServiceRegistry
2.NacosRegistration
3.NacosAutoServiceRegistration

NacosAutoServiceRegistration实现了服务向 Nacos 发起注册的功能，它继承自抽象类 AbstractAutoServiceRegistration
AbstractAutoServiceRegistration 中 bind()方法有一个@EventListener, 监听WebServerInitializedEvent事件
-> start()
   --> NacosAutoServiceRegistration.register()
   --> NacosServiceRegistry.register()
       ---> namingService.registerInstance(serviceId, instance); //nacos提供的SDK方法
       ---> beatReactor.addBeatInfo() //**心跳**检测机制(发送给服务端, 5s)
       ---> NamingProxy.registerService() //do注册 (NACOS_URL_INSTANCE:/nacos/v1/ns/instance)
            ----> index = (index + 1) % servers.size(); //集群模式下的轮询, 初始index随机  
            ----> callServer() //调用每个nacos服务
                 -----> 内部使用openAPI的形式HttpClient调用
   --> context.publishEvent() //发布实例注册完毕消息


放在springboot的流程中就是
refresh()->finishBeanFactoryInitialization()
->getBean  org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration
->getBean  nacosAutoServiceRegistration
->nacosAutoServiceRegistration


## 服务地址动态感知
subscribe()->hostReactor.getServiceInfo()->scheduleUpdateIfAbsent()->addTask()
每隔十秒客户端发送一次列表查询, 获得最新列表

## 监控服务端push
PushReceiver.run()  //当服务端发送push的时候监控(未看源码)

## 服务启动读取Bootstrap.yml加载远程配置到Environment
preparedEnvironment() //主要是找自动装配的类com.alibaba.cloud.nacos.NacosConfigBootStrapConfiguration
    中触发listeners 中的BootstrapApplicationListener加载远程配置
    ->bootstrapServiceContext()
    ->builder.sources(BootstrapImportSelectorConfiguration.class);
    ->selectImports()->
    List<String> names = new ArrayList<>(SpringFactoriesLoader.loadFactoryNames(BootstrapConfiguration.class, classLoader));
prepareContext() //确实可以算作容器前的准备了
    ->PropertySourceBootstrapConfiguration
    ->locator.locateCollection(environment); //加载远程配置
    ->locater.locate() //NacosPropertySourceLocator

## nacos config核心
### 继续跟进NacosPropertySourceLocator
locater.loacte()
->loadApplicationConfiguration()->loadNacosDataIfPresent()->loadNacosPropertySource()->build()->loadNacosData()
 ->ApplicationReadyEvent -->NacosContextRefresher监听

# nacos服务端(nacos-1.1.4)
## 服务注册
找到InstanceController
-> register()-> registerInstance()
   --> createEmptyService() //创建空服务
       ---> createServiceIfAbsent().putServiceAndInit()
            ----> putService(service); //service放入缓存
            ----> service.init();  //**心跳**检测机制(客户端健康检测, 5s)
                  ----->走到task的run方法中发现, nacos会隔段时间轮询客户端, 如果超时未响应则会标记为不健康, 然后发送服务变更事件
            ----> consistencyService.listen  //一致性检测
--> addInstance()  //添加实例

## 服务查询
InstanceController
-> list()
   --> doSrvIPXT()
       --->service.srvIPs() //获取指定服务下所有真实ip
       ... 

## 服务端挂掉
服务端挂掉会发送pushService(还未研究源码)

## 接收客户端心跳
找到InstanceController
-> beat()
   --> service.processClientBeat() //如果心跳对应的服务不存在, 则进行注册
       ---> HealthCheckReactor.scheduleNow(clientBeatProcessor);  //立即检查的一个线程, 如果ipport对的话更新时间, 并标记为健康状态

## raft(一致性同步数据)
先撸框架, 得空再看



# 二. nacos-2.x
结构发生了一些变化
**com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2.1.1.RELEASE**
新版 bind() 在 AbstractAutoServiceRegistration 的 onApplicationEvent 中
新版的用了async，又加了什么watch 看不懂

# 三. 总结
## 心跳
1.有客户端注册的时候，客户端维持5s一次的心跳发送给服务端，发送接口为/instance/beat，同时服务端检测一遍所有的客户端心跳是否超时，并清除
2.客户端调用/instance/beat时，服务端执行一个立即检查该心跳的方法，并标记该客户端为健康状态