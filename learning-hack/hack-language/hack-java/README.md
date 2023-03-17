# 基本名词
```text
1.jdbcrowsetimpl
https://www.4spaces.org/309.html  
JNDI 在 J2EE 应用程序中的主要角色就是提供间接层，这样组件就可以发现所需要的资源，而不用了解这些间接性。  
JNDI是一个接口规范 具体实现有rmi, ldap, dns, corba, nds, windows注册表等  

2.rmi and jrmp
https://cloud.tencent.com/developer/article/1106776  

3.rmi vs rpc
https://cloud.tencent.com/developer/article/1353191  
rmi通过 stub 和 skeleton 调用方法, 而rpc调用服务(一个服务可能有若干方法)  
rpc是协议rmi是实现, 就好像rmi是rpc的子集  

4.ldap: 
https://www.cnblogs.com/wilburxu/p/9174353.html
https://www.cnblogs.com/fusheng11711/p/11158692.html
LDAP（Light Directory Access Portocol），它是基于X.500标准的轻量级目录访问协议
目录数据需要存储在相关的数据库中 然后使用LDAP协议访问之

5.使用openldap
https://www.jb51.net/article/202232.htm

6.如何绕过高版本JDK限制进行JNDI注入利用
https://mp.weixin.qq.com/s/Dq1CPbUDLKH2IN0NA_nBDA

7.Tomcat下JNDI高版本绕过浅析
https://xz.aliyun.com/t/10829#toc-2

8.浅蓝, 探索高版本 JDK 下 JNDI 漏洞的利用方法
https://tttang.com/archive/1405/

7.其他文章
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
问题8：commons-collections的修复(16)


# 高版本jdk
## RMI Remote Object Payload(RROP)
攻击者实现一个RMI恶意远程对象并绑定到RMI Registry上，编译后的RMI远程对象类可以放在HTTP/FTP/SMB等服务器上，这个Codebase地址由远程服务器的 java.rmi.server.codebase 属性设置，供受害者的RMI客户端远程加载，RMI客户端在 lookup() 的过程中，会先尝试在本地CLASSPATH中去获取对应的Stub类的定义，并从本地加载，然而如果在本地无法找到，RMI客户端则会向远程Codebase去获取攻击者指定的恶意对象，这种方式将会受到 useCodebaseOnly 的限制。利用条件如下：
1.RMI客户端的上下文环境允许访问远程Codebase。
2.属性 java.rmi.server.useCodebaseOnly 的值必需为false。
然而从JDK 6u45、7u21开始，java.rmi.server.useCodebaseOnly 的默认值就是true。当该值为true时，将禁用自动加载远程类文件，仅从CLASSPATH和当前VM的java.rmi.server.codebase 指定路径加载类文件。
使用这个属性来`防止客户端VM从其他Codebase地址上动态加载类`，增加了RMI ClassLoader的安全性。
我们第一个payload(Factory) -> 受害者解码Reference时会去`我们指定的Codebase远程地址加载Factory类, 并非使用RMI Class Loading机制(不是加载class类而是加载Factory)`, 所以useCodebaseOnly影响不大
## RMI JNDI Reference Payload(RJRP)
(以下版本之前可以使用Factory+RMI)
但是在JDK 6u132, JDK 7u122, JDK `8u113` 中Java提升了JNDI 限制了Naming/Directory服务中JNDI Reference远程加载Object Factory类的特性。
系统属性 com.sun.jndi.rmi.object.trustURLCodebase、com.sun.jndi.cosnaming.object.trustURLCodebase 的默认值变为false，`即默认不允许从远程的Codebase加载Reference的工厂类（Factory Class）`。
如果需要开启 RMI Registry 或者 COS Naming Service Provider的远程类加载功能，需要将前面说的两个属性值设置为true。
NamingManager.getObjectFactoryFromReference()有加载class的逻辑
高版本影响的属性
- com.sun.jndi.rmi.object.trustURLCodebase
- com.sun.jndi.cosnaming.object.trustURLCodebase
- com.sun.jndi.ldap.object.trustURLCodebase
## LDAP JNDI Reference Payload(LJRP)
(以下版本之前可以使用LDAP)
除了RMI服务之外，JNDI还可以对接LDAP服务，LDAP也能返回JNDI Reference对象，利用过程与上面RMI Reference基本一致，只是lookup()中的URL为一个LDAP地址：ldap://xxx/xxx，由攻击者控制的LDAP服务端返回一个恶意的JNDI Reference对象。并且LDAP服务的Reference远程加载Factory类不受上一点中 com.sun.jndi.rmi.object.trustURLCodebase、com.sun.jndi.cosnaming.object.trustURLCodebase等属性的限制，所以适用范围更广。
不过在2018年10月，Java最终也修复了这个利用点，对LDAP Reference远程工厂类的加载增加了限制，在Oracle JDK 11.0.1、`8u191`、7u201、6u211之后 com.sun.jndi.ldap.object.trustURLCodebase 属性的默认值被调整为false，还对应的分配了一个漏洞编号CVE-2018-3149。
## 绕过JDK 8u191+等高版本限制
所以对于Oracle JDK 11.0.1、8u191、7u201、6u211或者更高版本的JDK来说，默认环境下之前这些利用方式都已经失效。然而，我们依然可以进行绕过并完成利用。两种绕过方法如下：
1.找到一个受害者本地CLASSPATH中的类作为恶意的Reference Object Factory工厂类，并利用这个本地的Factory类执行命令。
2.利用LDAP直接返回一个恶意的序列化对象，JNDI注入会对该对象进行反序列化操作，利用反序列化Gadget完成命令执行。