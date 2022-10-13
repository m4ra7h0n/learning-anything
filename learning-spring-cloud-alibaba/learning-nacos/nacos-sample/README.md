# nacos
## nacos-discovery
这里的nacos-discovery就相当于dubbo, 在分布式环境中作为注册中心, 以及rpc调用
## nacos-config
配置中心

## api
注册实例
/nacos/v1/ns/instance (POST)
serviceName, ip, port, (clusterName)
serviceName, instance

获取实例
/nacos/v1/ns/instance/list (GET)
serviceName, (clusters)

服务监听
/nacos/v1/ns/instance/list (GET)
