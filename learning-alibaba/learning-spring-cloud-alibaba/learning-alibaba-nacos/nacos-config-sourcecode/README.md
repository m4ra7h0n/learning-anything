# 名词
fixed polling: 固定间隔轮训(直接返回结果这种)
long pulling: 长轮询


https://developer.aliyun.com/article/860536
# nacos 1.4
## client
->ServerHttpAgent
//开启两个线程，一个线程服务，用于检测并提交LongPollingRunnable长轮询任务(3000条一个线程 去服务端pull)
->ClientWorker -> checkConfigInfo()  //长轮询机制

-> LongPollingRunnable.run()
//容灾本地文件
--> checkLocalConfig() //检查内存中的配置和本地配置是否一致
//检查服务配置
--> checkUpdateDataIds() -> checkUpdateConfigStr() //客户端实现长轮询
 ---> agent.httpPost()
//修改本地配置
--> getServerConfig() //根据变更的groupid等去服务端获取配置

## server
listener()
doPollingConfig() //轮询接口
// 长轮询
-> addLongPollingClient() //判断 设置时间(延迟30s) 封装成ClientLongPolling
 --> ClientLongPolling() //删除订阅关系 并返回变化的值
 --> onEvent             //30s之内如果发生配置变化则直接返回
// 兼容短轮询逻辑 获取result
// 返回值放入header
->


# nacos 2.0
放弃长轮询使用长连接
https://juejin.cn/post/6986887722283565069
ConfigService configService = NacosFactory.createConfigService(properties);
->反射NacosConfigService
--> ConfigFilterChainManage
--> ServerListManager
--> ClientWorker
 --> executeConfigListen()  //cacheMap分组 5分钟一次全量同步
--> ServerHttpAgent


# 其他
长连接 keep-alive
保活机制(学修netty/dubbo的时候还有可能出现)
https://blog.csdn.net/heroqiang/article/details/102320920