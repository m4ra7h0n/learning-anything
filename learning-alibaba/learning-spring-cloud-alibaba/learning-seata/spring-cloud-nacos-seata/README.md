1.5.2版本配置参考:
http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html
https://seata.io/zh-cn/docs/user/configuration/nacos.html
https://juejin.cn/post/7159090795960598565#heading-9
细节太多

seata/conf/application.example.yml是配置文件的样例

修改server端 seata/script/config-center/config.txt
~~~properties
#seata.service.vgroup-mapping.事务分组名=集群名称
service.vgroup_mapping.stock-service-group=default
service.vgroup_mapping.order-service-group=default
~~~
修改seata/conf/application.yml --> 两个nacos 一个db

启动seata-server
~~~bash
#向nacos中写入初始化配置
cd seata/script/config-center/nacos
sh nacos-config.sh 127.0.0.1 

#启动seata-server
cd bin
sh seata-server.sh -p 8091 -m db

# notic
# 要时刻查看 logs/start.out 查看服务是否启动
# 如果使用nacos 则在nacos的服务列表会注册一个seata-server
~~~

http://localhost:7091 是web端控制台

4.初始化数据库sql.sql

