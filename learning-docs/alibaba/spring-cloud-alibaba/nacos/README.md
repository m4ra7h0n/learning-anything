《nacos架构与原理》pdf

# nacos文件夹
nacos-bin.zip  ->  直接可执行的nacos
nacos-docker  ->  docker搭建nacos集群(某github项目, 开箱即用)

# nacos
日志文件:
logs/nacos.log

## nacos-1.1.4源码编译
mvn -Prelease-nacos clean install -U
命令目录: nacos-1.1.4/distribution/target/nacos-server-1.1.4/nacos/bin

## nacos使用
**启动**:  
Linux: `sh startup.sh -m standalone`  
Windows: `startup.cmd -m standalone`  
**登录**:  
http://127.0.0.1:8848/nacos  
nacos/nacos  
