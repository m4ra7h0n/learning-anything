# 基本名词
```text
1.jndi: https://www.4spaces.org/309.html  
JNDI 在 J2EE 应用程序中的主要角色就是提供间接层，这样组件就可以发现所需要的资源，而不用了解这些间接性。  
JNDI是一个接口规范 具体实现有rmi, ldap, dns, corba, nds, windows注册表等  

2.rmi and jrmp: https://cloud.tencent.com/developer/article/1106776  

3.rmi vs rpc: https://cloud.tencent.com/developer/article/1353191  
rmi通过 stub 和 skeleton 调用方法, 而rpc调用服务(一个服务可能有若干方法)  
rpc是协议rmi是实现, 就好像rmi是rpc的子集  

4.ldap: https://www.cnblogs.com/wilburxu/p/9174353.html
LDAP（Light Directory Access Portocol），它是基于X.500标准的轻量级目录访问协议
目录数据需要存储在相关的数据库中 然后使用LDAP协议访问之

5.使用openldap: https://www.jb51.net/article/202232.htm

```

# java历史漏洞jdk版本
version < jdk8u121 (jdk8u112)
version = jdk8u121
version > jdk8u191 (jdk8u192)
资源存放在 [learning-docs/hack/language/java/jdk]() 里请自取
或者去官网下载: https://www.oracle.com/sg/java/technologies/javase/javase8-archive-downloads.html

## version < jdk8u121
sun.rmi.registry.RegistryImpl_Skel#dispatch
