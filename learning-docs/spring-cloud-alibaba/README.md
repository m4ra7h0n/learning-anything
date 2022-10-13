# 文件夹
## nacos
nacos-bin.zip  ->  直接可执行的nacos
nacos-docker  ->  docker搭建nacos集群(某github项目, 开箱即用)

## Spring Cloud Alibaba 微服务原理与实战
nacos-1.1.4.zip  ->  pdf配套源码

## spring-cloud-alibaba 官方源码
asciidoc-zh是官方中文文档

# 网址资源   
spring-cloud官网: https://www.springcloud.cc/  
spring-cloud-alibaba: https://github.com/alibaba/spring-cloud-alibaba/
nacos官方文档: https://nacos.io/zh-cn/docs/quick-start.html  
nacos架构与原理雀语(文件夹有pdf): https://www.yuque.com/nacos/ebook/pb40qx

# 组件基本使用
## nacos
日志文件: 
logs/nacos.log

### nacos-1.1.4源码编译
mvn -Prelease-nacos clean install -U
命令目录: nacos-1.1.4/distribution/target/nacos-server-1.1.4/nacos/bin

### nacos使用  
**启动**:  
Linux: `sh startup.sh -m standalone`  
Windows: `startup.cmd -m standalone`  
**登录**:  
http://127.0.0.1:8848/nacos  
nacos/nacos  

## sentinel
