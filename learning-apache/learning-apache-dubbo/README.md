# intro    
dubbo-sample部分是跟着《spring cloud alibaba原理与实战》搞的     
dubbo-sample/dubbbo-sample-spring-boot中添加了nacos作为注册中心

# maven的几个版本区别
groupId: org.apache.dubbo

## artifactId: dubbo-spring-boot-starter(0)
引入dubbo-spring-boot-autoconfigure(1)
(1)引入dubbo-spring-boot-autoconfigure-compatible(2) 和 dubbo(3)
(2)引入dubbo(3)

## artifactId: dubbo(3)
和dubbo-spring-boot-starter重复

# dubbo 踩坑
provider发布之后如果添加了maven的pom, 必须重启, 否则consumer无法调用

# dubbo特性    
  
## dubboSPI      
1. 官方定义SPI接口(service provider interface), 实现方实现接口进行扩展    
2.调用的时候通过ExtensionLoader.getExtensionLoader(SPIInterface.class).getExtension("extensionName")获得该扩展    
3.不同于javaSPI, dubboSPI需要resources/META-INF/dubbo/下添加以接口包名为名称的文件, 内容为key-value形式, key作为调用的extensionName    
  
## dubboAdaptive  
1.dubboSPI其中的一种机制, 用来动态加载类扩展类, 可以在接口方法上加, 也可在类上加, 在接口方法上加使用代理模式(javassist), 而在类上加则不会有代理  
2.编写的时候需要一个URL对象, 一个@Adaptive注解  
3.使用如下方式调用: ExtensionLoader.getExtensionLoader(SPIInterface.class).getAdaptiveExtension()  
4.SPI("className")作为默认加载的类  
5.只能执行@Adaptive注解的方法, 执行不带注解的方法会报错  
  
## dubboComponentScan    
作为springboot组件扫描的扩展, dubbo实现了spring的beanPostProcessor, 在执行spring流程的时候会有一个while循环, 依次执行beanPostProcessor的postProcessBeanDefinitionRegistry()方法(跟踪入口)  
核心只有三部分:  
1.获取扫描路径    
2.扫描@Service并注入到IOC容器中 
3.扫描@Reference并注入到IOC容器中   