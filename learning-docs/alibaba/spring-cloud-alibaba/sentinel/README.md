# 官方文档
https://sentinelguard.io/zh-cn/index.html

# 启动
使用jdk-16
java -jar sentinel-dashboard-1.8.5.jar --server.port=8090

# 登录
http://localhost:8090
sentinel/sentinel

# 限流日志
~/logs/csp/${appName}-metrics.log.xxx
timestamp|yyyy-MM-dd HH:mm:ss|resource|passQps|blockQps|successQps|exceptionQps|rt|occupiedPassQps|concurrency|classification
passQps: 通过的请求
blockQps: 被阻止的请求
successQps: 成功执行完成的请求
exceptionQps: 用户自定义的异常
rt: 平均响应时长
occupiedPassQps: 优先通过的请求
concurrency: 并发量
classification: 资源类型

# sentinel-examples
例子还是比较详细的  如果学习sentinel + dubbo  学习此文档要详细于 dubbo文档

# 其他说明
sentinel-examples是官方examples源码