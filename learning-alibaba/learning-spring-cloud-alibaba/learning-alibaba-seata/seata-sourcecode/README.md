# 看源码 写例子
// 版本有出入
https://www.cnblogs.com/crazymakercircle/p/15313951.html#autoid-h2-7-13-0

# seata 1.4.2
SeataPropertiesAutoConfiguration

SeataDataSourceAutoConfiguration
-> SeataDataSourceBeanPostProcessor  //bean后置处理器
-> seataAutoDataSourceProxyCreator   //自动数据库代理 的 创建者

SeataAutoConfiguration
-> GlobalTransactionScanner   // afterPropertiesSet()
    // TM初始化
   -> tmNettyClientRemotingClient.init()
      -> registerProcessor()
         // 1.registry TC response processor
         // 2.registry heartbeat message processor
         -> HashMap<Integer/*MessageType*/, Pair<RemotingProcessor, ExecutorService>>   // 注册到这里
      -> AbstractNettyClientRemotingClient.init()
    // RM初始化

HttpAutoConfiguration