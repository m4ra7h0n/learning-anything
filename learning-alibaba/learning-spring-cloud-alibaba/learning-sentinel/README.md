# sentinel 结构
责任链为整体结构  各个功能是基于ProcessorSlot的spi扩展功能  
见https://sentinelguard.io/zh-cn/docs/basic-implementation.html  
| 插槽                 | 功能                                                                       |
|--------------------|--------------------------------------------------------------------------|
| NodeSelectorSlot   | 负责收集资源的路径，并将这些资源的调用路径，以树状结构存储起来，用于根据调用路径来限流降级；                           |
| ClusterBuilderSlot | 则用于存储资源的统计信息以及调用者信息，例如该资源的 RT, QPS, thread count 等等，这些信息将用作为多维度限流，降级的依据； |
| StatisticSlot      | 则用于记录、统计不同纬度的 runtime 指标监控信息；                                            |
| FlowSlot           | 则用于根据预设的限流规则以及前面 slot 统计的状态，来进行流量控制；                                     |
| AuthoritySlot      | 则根据配置的黑白名单和调用来源信息，来做黑白名单控制；                                              |
| DegradeSlot        | 则通过统计信息以及预设的规则，来做熔断降级；                                                   |
| SystemSlot         | 则通过系统的状态，例如 load1 等，来控制总的入口流量；                                           |




# nacos动态流控规则
起因  sentinel是内存存储  关闭服务后配置消失  于是需要存储的地方
需要注意的地方是 **sentinel不支持修改dashboard之后同步到nacos** 所以nacos近作为配置中心存储数据
如果修改nacos虽然可以同步到sentinel 但是危险系数高 不建议 (好好地dashboard它不香吗, 非得改nacos)
所以就引申出了修改源码一说 dashboard修改后自动同步到nacos
这里和推/拉模式不冲突 拉是sentinel定期拉取nacos  推是nacos变动推送给sentinel  始终没有修改sentinel推送给nacos(so修改源码, 暂时未做)

# sentinel dubbo
consumer/provider加流控规则都行

# sentinel adapter
dubbo-adapter主要包括针对 Service Provider 和 Service Consumer 实现的 Filter

# dubbo接入sentinel
7.8.1未搞