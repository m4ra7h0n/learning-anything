  
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
2.starter中还要定义某个类的自动配置类, 该类需要添加@EnableConfigurationProperties注解, 参数为Properties类, 并将该类写入resources/META-INF/spring.factories作为springboot自动加载配置的实现(SpringFactoriesLoader->loadSpringFactories()加载)    
3.resources/META-INF/additional-spring-configuration-metadata.json作为application.properties的提示信息, 可设置默认值    
  
# 事件监听机制(EventListener)  
看到nacos源码的时候发现了这个东西  
listen包里写了demo, 有三个东西  
1.event(事件)  
2.listener(监听事件变动)  
3.publisher(改变事件)  
4.multicaster(广播)
很好理解, 发布订阅模式  
  
  
# springboot启动源码(目前只粗略的读一下, 后续要着重研究)  
SpringApplication.run() 打断点, 一直跟进到org.springframework.boot.SpringApplication.run()  
  
-> createBootstrapContext() //创建上下文对象  
  
-> getRunListeners().starting() //获取并启动监听器(EventPublishingRunListener, 从spring.factories中获取  
  
-> prepareEnvironment() //准备环境  
    --> ConfigurationPropertySources.attach() //将ConfigurationProperties注解装入  
    --> listeners.environmentPrepared() //多播环境准备好的事件 唤醒事件监听器(日志监听器, 环境后置处理监听器, 文件编码监听器, 重启监听器, 委派监听器等, 在源码jar中的spring.factories中定义  
        ---> multicastEvent().invokeListener().EnvironmentPostProcessorApplicationListener //等各种Listener.onApplicationEvent()  
            ----> EnvironmentPostProcessor //环境后置处理器, 定义在spring.factories中  
    ...  
  
-> printBanner() //打印banner  
  
-> createApplicationContext() //创建spring容器(在源码jar包的spring.factories中定义, 共两个)  
    --> AnnotationConfigServletWebServerApplicationContext().create() //  
        ---> beanFactory //可选, 用于生成bean对象, 默认DefaultListableBeanFactory  
        ---> reader //注解配置读取器, 用于读取配置并注册bean  
        ---> scanner //扫描指定包目录bean对象  
    --> AnnotationConfigReactiveWebServerApplicationContext().create() //  
    ...  
  
-> prepareContext() //spring容器前置处理  
    --> setEnvironment()  
    --> postProcessApplicationContext()  
    --> applyInitializers() //初始化器, 源于spring.factories(运行时有7个, 未研究)  
    --> listeners.contextPrepared() //通知容器准备完成(广播, 多线程)  
      # 下面三个都不知道干啥的  
    --> springBootBanner/springApplicationArguments //添加单例bean  
    --> setAllowCircularReferences()/setAllowBeanDefinitionOverriding() //boolean类型，不知道干啥的  
    --> PropertySourceOrderingBeanFactoryPostProcessor //添加后置处理器  
    ...  
    --> load() //创建BeanDefinationLoader, 加载根目录的启动类, 根据类型加载字节码(如下四种, 且从上到下只能走一个)  
        ---> Class<?>  
        ---> Resource  
        ---> Package  
        ---> CharSequence  
    --> listeners.contextLoaded():发布容器已加载事件  
  
-> refreshContext():刷新容器  
    --> shutdownHook.registerApplicationContext():优雅关闭spring?  
    --> refresh()  
        ---> prepareRefresh(); //刷新前的预处理  
        ---> obtainFreshBeanFactory(); //获取 beanFactory  
        ---> prepareBeanFactory(beanFactory); //预处理 beanFactory，向容器中添加一些组件  
             ----> ignoreDependencyInterface(); //设置忽略自动装配的接口，即不能通过注解自动注入  
             ----> registerResolvableDependency(); //注册可以解析的自动装配类，即可以在任意组件中通过注解自动注入  
             ----> 添加编译时的 AspectJ -> 注册 environment 组件 -> 注册 systemProperties 组件 -> 注册 systemEnvironment 组件  
        ---> postProcessBeanFactory(beanFactory); //子类通过重写这个方法可以在 BeanFactory 创建并与准备完成以后做进一步的设置  
        ---> invokeBeanFactoryPostProcessors(beanFactory); //执行 BeanFactoryPostProcessor 方法，beanFactory 后置处理器  
             ----> postProcessBeanDefinitionRegistry(registry) //执行BeanDefinitionRegistryPostProcessor自定义方法  
             ----> postProcessBeanDefinitionRegistry(registry) //执行BeanFactoryPostProcessor自定义方法  
             ... 太多了一大堆看不懂  
        ---> registerBeanPostProcessors(beanFactory); // 注册拦截bean创建的bean处理器
             //先注册实现PriorityOrdered 的 bean后置处理器
             //再注册实现Ordered 的 bean后置处理器
             ----> beanFactory.getBean(GlobalTransactionScanner, BeanPostProcessor.class)  //分析seata的postprocessor
                   //创建bean实例
                   ----> createBean()
                         ----> resolveBeanClass()  //动态加载类
                         ----> prepareMethodOverrides()  //
                         ----> resolveBeforeInstantiation() //允许bean后置处理器返回一个代理
                         ----> doCreateBean(beanName, mbdToUse, args);
                               ----> initializeBean(beanName, exposedObject, mbd);
                                     ----> afterPropertiesSet(); //执行创建TM和RM
                    
             //最后注册所有常规的bean后置处理器





        ---> initMessageSource(); //初始化 MessageSource 组件（做国际化功能；消息绑定，消息解析）  
        ---> initApplicationEventMulticaster(); //初始化事件派发器，在注册监听器时会用到  
             ----> 可自定义applicationEventMulticaster  
        ---> onRefresh(); //留给子容器（子类），子类重写这个方法，在容器刷新的时候可以自定义逻辑，web 场景下会使用  
        ---> registerListeners(); //注册监听器，派发之前步骤产生的一些事件（可能没有）  
             ----> 自定义ApplicationListener  
        ---> finishBeanFactoryInitialization(beanFactory); //初始化所有的非单实例 bean  
             ----> beanFactory.preInstantiateSingletons();  // Instantiate all remaining (non-lazy-init) singletons.  
             .... 神他妈多, 循环依赖、bean 后置处理器运用、aop 代理(我先不看, 先撸框架)  
        ---> finishRefresh(); //发布容器刷新完成事件  
             ---->WebServerInitializedEvent
  
-> afterRefresh() //spring容器后置处理  
-> listeners.started(); //发布全部完成事件  
-> callRunners();  
   --> AnnotationAwareOrderComparator.sort(); //根据Order定义的优先级排序  
   --> ApplicationRunner()/CommandLineRunnder(); //调用二者  
  