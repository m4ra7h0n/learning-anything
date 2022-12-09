架构参照seata-samples
细节上修改 springboot-dubbo-seata 为 cloud 以及 某些减扣为负数的bug

测试的时候 模块分别抛出异常 结果成功
如果模块执行失败返回失败状态码 交给TM抛出异常 则失败