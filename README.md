# learning anything 

笔者学习顺序依次如下：  
1.《Maven实战》  
2.《Spring Cloud Alibaba原理与实战》中途学习《Mybatis从入门到精通》

所有pdf资源放在阿里云盘(永远有效): https://www.aliyundrive.com/s/ngpTUNYpZ55

# 完成列表
- learning-algorithm
- learning-alibaba
    - spring-cloud-alibaba
        - [ ] nacos
          - [ ] 源码
            - [x] discovery
            - [ ] config
        - [ ] rocketmq
            - [ ] 源码
        - [ ] seata
            - [ ] 源码
        - [ ] sentinel
            - [x] 流控
            - [x] 热点参数
            - [ ] 源码
- learning-apache
    - [ ] dubbo
        - [ ] rpc
            - [ ] dubbo
            - [ ] grpc
        - [x] spi
        - [ ] 源码
    - [x] maven
      - [x] 基础
      - [x] 插件编写
- learning-database
    - [ ] mysql
        - [x] mybatis
        - [ ] mybatis-plus
        - [ ] hibernate
    - [ ] redis
- learning-docs
- learning-front
    - [ ] vue
- learning-google
  - [ ] grpc
  - [ ] guava
- learning-hack
- learning-spring-boot
  - [x] 手写starter
  - [ ] 源码


# ps学习建议  
## 方法论
学习应该是学起来有干劲，学不够，爽，越学越想学，如何达到呢？  
1.目标驱动学习
1)针对开发技术这块，一来是项目源码驱动学习，先找个项目源码，为了搞清楚整个项目的架构(系统性)以及具体代码实现，不得不学习其中每个模块的细节，阅读源码和调试的时候遇到问题和疑惑会越来越多，随着思考的深入，产生的问题在网上也逐渐搜索不到了。  (这里如果是按照pdf实践的，可直接跳到第三步)  
2)于是引出第二点，你想详细的学习这门技术，就去找pdf，从上到下撸，一本一本看  
3)看到后面想继续深入，于是找找某个组件的源码解析pdf，慢慢学呗，反正学完面试的时候不冷场，可以吊打面试官:)  
目标->问题->思考->寻找答案->获得成就感  

2.系统化驱动学习  
单点研究nacos并没有给我带来很大的兴趣，只有把它放在整个生态上的某个位置，了解到它的那些功能都是做什么的，才有想要研究好它的欲望。
同理学习mybatis-plus的时候刷文档也要搞清楚他的整体结构(比方说service/mapper层的crud, wrapper/lambdawrapper)  然后在逐点学习

2.实践驱动学习  
实践虽然会遇到各种问题，但是解决后会有一种油然而生的踏实感，一种真真实实的获得感，必要实践  

3.工整考究驱动学习  
实践的时候要工工整整的，要做就做最好，这样才会引起自豪的心情，想去维护好这个项目，让它越来越好，越来越充实和完美  

## 学习踩坑：  
1.学不懂  
基础不牢，如果你不懂springboot，直接学习spring-cloud技术栈，大半会觉得学起来很吃力，这里搜一搜，那里搜一搜，结果调试好久也调试不通。尝试先放开spring-cloud，找一本springboot实践的pdf来学学，你会很有收获。记住欲速而不达。  
2.步骤都对就是报错  
两种情况, 其一是不同版本需要的组件的版本也有差异, 需要的组件也有差异, 尝试多试一试不同的组件, 比如教程上写的是引入zookeeper模块, 但实际上需要引入spring-cloud-starter-zookeeper-discovery才能调试通过  
第二是由于挪动包, 改包名导致的不知原因的错误, 试一下重启idea, 仍不行试一试保存内容, 删掉模块重新建一个  

## 反思
### 一 
有时候在想, 比如读到dubbo-spi机制源码那块, 里面有一个wrapperClasses, 以前没见过包装类可能就掠过了, 我自己分析的时候真没注意这块, 看教程才注意
有两种方案:
1.前期尽量跟着各种教程来学源码等
2.当教程枯竭, 读到生僻的源码, 就一定要细致, 最好能搞出流程图, 分析好每个地方的作用

### 二
如果pdf教程中的版本比较低 可以直接根据官方给出的sample 进行编写代码

### 三
源码不要在学习组件的时候阅读, 源码要在熟练使用组件能做项目的前提下去读, 这样带着问题和好奇心会读的更好

### 四
集群不要先配置 项目从简单入手 集群作为优化的方式