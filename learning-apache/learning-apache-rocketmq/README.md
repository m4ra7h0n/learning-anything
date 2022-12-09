4.x的文档就是个jiba  
还好5的文档稍微详细一点

参考官网
# nameserver
先启动 nameserver  
nohup sh mqnamesrv &

# broker
conf/2m-2s-async/broker-a.properties中添加如下两个配置
enablePropertyFilter=true
autoCreateTopicEnable=true
使用时: -c conf/2m-2s-async/broker-a.properties

再启动 broker  
nohup sh mqbroker -n localhost:9876 autoCreateTopicEnable=true &

关闭  
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv


# connect
https://github.com/apache/rocketmq-connect
1.创建测试topic(一定要配置 ROCKETMQ_HOME )
sh ${ROCKETMQ_HOME}/bin/mqadmin updateTopic -t fileTopic -n localhost:9876 -c DefaultCluster -r 8 -w 8

2.构建connect
git clone https://github.com/apache/rocketmq-connect.git
mvn -Prelease-connect -DskipTests clean install -U

3.运行worker
cd distribution/target/rocketmq-connect-0.0.1-SNAPSHOT/rocketmq-connect-0.0.1-SNAPSHOT
sh bin/connect-standalone.sh -c conf/connect-standalone.conf &

4.source connector
测试
touch test-source-file.txt
echo "Hello \r\nRocketMQ\r\n Connect" >> test-source-file.txt
curl -X POST -H "Content-Type: application/json" http://127.0.0.1:8082/connectors/fileSourceConnector -d '{"connector.class":"org.apache.rocketmq.connect.file.FileSourceConnector","filename":"test-source-file.txt","connect.topicname":"fileTopic"}'

5. sink connector
   curl -X POST -H "Content-Type: application/json" http://127.0.0.1:8082/connectors/fileSinkConnector -d '{"connector.class":"org.apache.rocketmq.connect.file.FileSinkConnector","filename":"test-sink-file.txt","connect.topicnames":"fileTopic"}'
   cat test-sink-file.txt

日志文件位置
~/logs/rocketmqconnect


5.0.0官网: https://rocketmq.apache.org/zh/docs

我想吐槽一下 就离谱
4.9版本的官网的讲解做的啥玩意 要直接学5版本 然后还一堆坑？
完事5不会没办法啊 又回去搞4 还刚给删了

个人理解一下consumer的pull和push模式
pull比较适合消息频繁大量产生的场景(日志) 参考kafka
push比较适合变动比较少的场景(rocketmq的push底层原理是长轮询 并非真正push)

stream文档: https://github.com/apache/rocketmq-streams/tree/main/rocketmq-streams-examples
connect文档: https://rocketmq-1.gitbook.io/rocketmq-connector/
类似于logstash

# rocketmq与kafka对比
kafka不支持消费失败重试/定时消息/事务消息/顺序消费，难以支撑淘宝订单交易充值等业务。淘宝中间件团队参考kafka设计的RocketMQ