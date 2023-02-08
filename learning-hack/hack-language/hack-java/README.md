# 基本名词
```text
1.jndi: https://www.4spaces.org/309.html  
JNDI 在 J2EE 应用程序中的主要角色就是提供间接层，这样组件就可以发现所需要的资源，而不用了解这些间接性。  
JNDI是一个接口规范 具体实现有rmi, ldap, dns, corba, nds, windows注册表等  
2.rmi and jrmp: https://cloud.tencent.com/developer/article/1106776  
3.rmi vs rpc: https://cloud.tencent.com/developer/article/1353191  
rmi通过 stub 和 skeleton 调用方法, 而rpc调用服务(一个服务可能有若干方法)  
rpc是协议rmi是实现, 就好像rmi是rpc的子集  
4.ldap: 
https://www.cnblogs.com/wilburxu/p/9174353.html
https://www.cnblogs.com/fusheng11711/p/11158692.html
LDAP（Light Directory Access Portocol），它是基于X.500标准的轻量级目录访问协议
目录数据需要存储在相关的数据库中 然后使用LDAP协议访问之
5.使用openldap: https://www.jb51.net/article/202232.htm

6.其他文章
http://wjlshare.com/archives/1522
https://paper.seebug.org/1091/
https://xz.aliyun.com/t/7930
https://xz.aliyun.com/t/7932
https://xz.aliyun.com/t/8247
https://y4er.com/post/java-rmi/
https://www.f4de.ink/pages/152581/#rmi%E5%92%8Cjndi
https://mp.weixin.qq.com/s?__biz=MzUyMzczNzUyNQ==&mid=2247485809&idx=3&sn=36f96dfb41bf03cebc4c92e63cd4c181
https://drops.blbana.cc/2020/04/16/Fastjson-JdbcRowSetImpl%E5%88%A9%E7%94%A8%E9%93%BE/
https://www.oreilly.com/library/view/learning-java/1565927184/ch11s04.html
https://www.anquanke.com/post/id/199481#h3-10
https://paper.seebug.org/1091/#java-rmi_3
https://www.smi1e.top/java%E4%BB%A3%E7%A0%81%E5%AE%A1%E8%AE%A1%E5%AD%A6%E4%B9%A0%E4%B9%8Bjndi%E6%B3%A8%E5%85%A5/
```

# java历史漏洞jdk版本
version < jdk8u121 (jdk8u112)
version = jdk8u121
version > jdk8u191 (jdk8u192)
资源存放在 [learning-docs/hack/language/java/jdk]() 里请自取
或者去官网下载: https://www.oracle.com/sg/java/technologies/javase/javase8-archive-downloads.html

## version < jdk8u121
sun.rmi.registry.RegistryImpl_Skel#dispatch

# p牛文章学习后待解决问题
问题1：如何从头跟踪codebase引发的漏洞源码  
问题2：读懂此协议(RMI3):  
https://docs.oracle.com/javase/8/docs/platform/serialization/spec/protocol.html#a8130
问题3：研究php和python的反序列化漏洞(反序列化1)
http://wjlshare.com/archives/1522
问题4：搞清楚调用链中的invoke到底是什么
问题5: 使用webapp搭建一个shiro
问题6: debug Tomcat对类的加载逻辑(漫谈15), 并debug shiro具体的编码位置
https://blog.zsxsoft.com/post/35
http://www.rai4over.cn/2020/Shiro-1-2-4-RememberMe%E5%8F%8D%E5%BA%8F%E5%88%97%E5%8C%96%E6%BC%8F%E6%B4%9E%E5%88%86%E6%9E%90-CVE-2016-4437/
问题7：尝试使用jrmp解决tomcat无法通过shiro-550的问题
http://blog.orange.tw/2018/03/pwn-ctf-platform-with-java-jrmp-gadget.html