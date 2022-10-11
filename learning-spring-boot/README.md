# intro
spring-boot-starter-redis以及spring-boot-demo是《spring cloud alibaba原理与实战》第三章  


# how to learn:
官方github+官方文档(先搁置了，等做一些springcloud项目之后再来刷可能会更有感觉，比如一些重要的，一些不重要的，一些新的可以优化的)
有些pdf写的不是很清楚，试错成本比较高，先挑好的pdf来学了，所以先去学那个spring-cloud-alibaba


# springboot特性

## EnableAutoConfiguration自动装配
@EnableAutoConfiguration注解中实现了@Import(AutoConfigurationImportSelector.class)
AutoConfigurationImportSelector.class继承了ImportSelector, 其中需要定义selectImports()函数返回自动加载的类的名字  

## @conditional注解
按照条件加载bean, 自定义需要继承Condition  
包括ConditionalOnBean: 存在Bean的时候加载  
等  

# starter是什么  
1.starter中定义Properties类, 用于application.properties中的配置选项, 该类需要添加注解@ConfigurationProperties(prefix = "xjj.redisson"), 其中perfix为前缀  
2.starter中还要定义某个类的自动配置类, 该类需要添加@EnableConfigurationProperties注解, 参数为Properties类, 并将该类写入resources/META-INF/spring.factories作为springboot自动加载配置的实现  
3.resources/META-INF/additional-spring-configuration-metadata.json作为application.properties的提示信息, 可设置默认值  