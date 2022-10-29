1 先写registry.conf type设置为注册中心类型
2 修改server端 seata/script/config-center/config.txt

~~~properties
service.vgroup_mapping.stock-service-group=default
service.vgroup_mapping.order-service-group=default
~~~

3.启动seata-server
~~~bash
#向nacos中写入初始化配置
cd seata/script/config-center/nacos
sh nacos-config.sh 127.0.0.1 

#启动seata-server
cd bin
sh seata-server.sh -p 8091 -m file 
~~~

4.初始化数据库sql.sql