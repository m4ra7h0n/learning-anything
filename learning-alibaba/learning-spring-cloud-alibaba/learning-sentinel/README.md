# nacos动态流控规则
起因  sentinel是内存存储  关闭服务后配置消失  于是需要存储的地方
需要注意的地方是 **sentinel不支持修改dashboard之后同步到nacos** 所以nacos近作为配置中心存储数据
如果修改nacos虽然可以同步到sentinel 但是危险系数高 不建议 (好好地dashboard它不香吗, 非得改nacos)
所以就引申出了修改源码一说 dashboard修改后自动同步到nacos
这里和推/拉模式不冲突 拉是sentinel定期拉取nacos  推是nacos变动推送给sentinel  始终没有修改sentinel推送给nacos(so修改源码, 暂时未做)

# sentinel dubbo
consumer/provider加流控规则都行