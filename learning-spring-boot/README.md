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

# 事件监听机制(EventListener)
看到nacos源码的时候发现了这个东西
listen包里写了demo, 有三个东西
1.event(事件)
2.listener(监听事件变动)
3.publisher(改变事件)
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
        ---> registerBeanPostProcessors(beanFactory); //注册 BeanPostProcessors，bean 后置处理器
        ---> initMessageSource(); //初始化 MessageSource 组件（做国际化功能；消息绑定，消息解析）
        ---> initApplicationEventMulticaster(); //初始化事件派发器，在注册监听器时会用到
        ---> onRefresh(); //留给子容器（子类），子类重写这个方法，在容器刷新的时候可以自定义逻辑，web 场景下会使用
        ---> registerListeners(); //注册监听器，派发之前步骤产生的一些事件（可能没有）
        ---> finishBeanFactoryInitialization(beanFactory); //初始化所有的非单实例 bean
        ---> finishRefresh(); //发布容器刷新完成事件

-> afterRefresh() //spring容器后置处理
-> listeners.started(); //发布全部完成事件
-> callRunners();
   --> AnnotationAwareOrderComparator.sort(); //根据Order定义的优先级排序
   --> ApplicationRunner()/CommandLineRunnder(); //调用二者


public static void invokeBeanFactoryPostProcessors(
ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
// beanFactoryPostProcessors 这个参数是指用户通过 AnnotationConfigApplicationContext.addBeanFactoryPostProcessor() 方法手动传入的 BeanFactoryPostProcessor，没有交给 spring 管理
// Invoke BeanDefinitionRegistryPostProcessors first, if any.
// 代表执行过的 BeanDefinitionRegistryPostProcessor
Set<String> processedBeans = new HashSet<>();

	if (beanFactory instanceof BeanDefinitionRegistry) {
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
		// 常规后置处理器集合，即实现了 BeanFactoryPostProcessor 接口
		List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
		// 注册后置处理器集合，即实现了 BeanDefinitionRegistryPostProcessor 接口
		List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();
		// 处理自定义的 beanFactoryPostProcessors（指调用 context.addBeanFactoryPostProcessor() 方法），一般这里都没有
		for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
			if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
				BeanDefinitionRegistryPostProcessor registryProcessor =
						(BeanDefinitionRegistryPostProcessor) postProcessor;
				// 调用 postProcessBeanDefinitionRegistry 方法
				registryProcessor.postProcessBeanDefinitionRegistry(registry);
				registryProcessors.add(registryProcessor);
			}
			else {
				regularPostProcessors.add(postProcessor);
			}
		}

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		// Separate between BeanDefinitionRegistryPostProcessors that implement
		// PriorityOrdered, Ordered, and the rest.
		// 定义一个变量 currentRegistryProcessors，表示当前要处理的 BeanFactoryPostProcessors
		List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

		// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
		// 首先，从容器中查找实现了 PriorityOrdered 接口的 BeanDefinitionRegistryPostProcessor 类型，这里只会查找出一个【ConfigurationClassPostProcessor】
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
		for (String ppName : postProcessorNames) {
			// 判断是否实现了 PriorityOrdered 接口
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				// 添加到 currentRegistryProcessors
				currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
				// 添加到 processedBeans，表示已经处理过这个类了
				processedBeans.add(ppName);
			}
		}
		// 设置排列顺序
		sortPostProcessors(currentRegistryProcessors, beanFactory);
		// 添加到 registry 中
		registryProcessors.addAll(currentRegistryProcessors);
		// 执行 [postProcessBeanDefinitionRegistry] 回调方法
		invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
		// 将 currentRegistryProcessors 变量清空，下面会继续用到
		currentRegistryProcessors.clear();

		// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
		// 接下来，从容器中查找实现了 Ordered 接口的 BeanDefinitionRegistryPostProcessors 类型，这里可能会查找出多个
		// 因为【ConfigurationClassPostProcessor】已经完成了 postProcessBeanDefinitionRegistry() 方法，已经向容器中完成扫描工作，所以容器会有很多个组件
		postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
		for (String ppName : postProcessorNames) {
			// 判断 processedBeans 是否处理过这个类，且是否实现 Ordered 接口
			if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
				currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
				processedBeans.add(ppName);
			}
		}
		// 设置排列顺序
		sortPostProcessors(currentRegistryProcessors, beanFactory);
		// 添加到 registry 中
		registryProcessors.addAll(currentRegistryProcessors);
		// 执行 [postProcessBeanDefinitionRegistry] 回调方法
		invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
		// 将 currentRegistryProcessors 变量清空，下面会继续用到
		currentRegistryProcessors.clear();

		// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
		// 最后，从容器中查找剩余所有常规的 BeanDefinitionRegistryPostProcessors 类型
		boolean reiterate = true;
		while (reiterate) {
			reiterate = false;
			// 根据类型从容器中查找
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				// 判断 processedBeans 是否处理过这个类
				if (!processedBeans.contains(ppName)) {
					// 添加到 currentRegistryProcessors
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 添加到 processedBeans，表示已经处理过这个类了
					processedBeans.add(ppName);
					// 将标识设置为 true，继续循环查找，可能随时因为防止下面调用了 invokeBeanDefinitionRegistryPostProcessors() 方法引入新的后置处理器
					reiterate = true;
				}
			}
			// 设置排列顺序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 添加到 registry 中
			registryProcessors.addAll(currentRegistryProcessors);
			// 执行 [postProcessBeanDefinitionRegistry] 回调方法
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 将 currentRegistryProcessors 变量清空，因为下一次循环可能会用到
			currentRegistryProcessors.clear();
		}

		// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
		// 现在执行 registryProcessors 的 [postProcessBeanFactory] 回调方法
		invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
		// 执行 regularPostProcessors 的 [postProcessBeanFactory] 回调方法，也包含用户手动调用 addBeanFactoryPostProcessor() 方法添加的 BeanFactoryPostProcessor
		invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
	}

	else {
		// Invoke factory processors registered with the context instance.
		invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
	}

	// Do not initialize FactoryBeans here: We need to leave all regular beans
	// uninitialized to let the bean factory post-processors apply to them!
	// 从容器中查找实现了 BeanFactoryPostProcessor 接口的类
	String[] postProcessorNames =
			beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

	// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
	// Ordered, and the rest.
	// 表示实现了 PriorityOrdered 接口的 BeanFactoryPostProcessor
	List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
	// 表示实现了 Ordered 接口的 BeanFactoryPostProcessor
	List<String> orderedPostProcessorNames = new ArrayList<>();
	// 表示剩下来的常规的 BeanFactoryPostProcessors
	List<String> nonOrderedPostProcessorNames = new ArrayList<>();
	for (String ppName : postProcessorNames) {
		// 判断是否已经处理过，因为 postProcessorNames 其实包含了上面步骤处理过的 BeanDefinitionRegistry 类型
		if (processedBeans.contains(ppName)) {
			// skip - already processed in first phase above
		}
		// 判断是否实现了 PriorityOrdered 接口
		else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
			priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
		}
		// 判断是否实现了 Ordered 接口
		else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
			orderedPostProcessorNames.add(ppName);
		}
		// 剩下所有常规的
		else {
			nonOrderedPostProcessorNames.add(ppName);
		}
	}

	// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
	// 先将 priorityOrderedPostProcessors 集合排序
	sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
	// 执行 priorityOrderedPostProcessors 的 [postProcessBeanFactory] 回调方法
	invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

	// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
	// 接下来，把 orderedPostProcessorNames 转成 orderedPostProcessors 集合
	List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>();
	for (String postProcessorName : orderedPostProcessorNames) {
		orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
	}
	// 将 orderedPostProcessors 集合排序
	sortPostProcessors(orderedPostProcessors, beanFactory);
	// 执行 orderedPostProcessors 的 [postProcessBeanFactory] 回调方法
	invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

	// Finally, invoke all other BeanFactoryPostProcessors.
	// 最后把 nonOrderedPostProcessorNames 转成 nonOrderedPostProcessors 集合，这里只有一个，myBeanFactoryPostProcessor
	List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
	for (String postProcessorName : nonOrderedPostProcessorNames) {
		nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
	}
	// 执行 nonOrderedPostProcessors 的 [postProcessBeanFactory] 回调方法
	invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

	// Clear cached merged bean definitions since the post-processors might have
	// modified the original metadata, e.g. replacing placeholders in values...
	// 清除缓存
	beanFactory.clearMetadataCache();
}
