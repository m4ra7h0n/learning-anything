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