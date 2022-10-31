# nacos 1.4
长轮询机制
ServerHttpAgent
ClientWorker
开启两个线程，一个线程服务，用于检测并提交LongPollingRunnable长轮询任务(3000条一个线程 去服务端pull)

# nacos 2.0

ConfigService configService = NacosFactory.createConfigService(properties);
->反射NacosConfigService
--> ConfigFilterChainManage
--> ServerListManager
--> ClientWorker
--> ServerHttpAgent