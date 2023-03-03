https://www.rc.sb/fastjson/#fastjson1280
https://y4er.com/posts/fastjson-learn
https://paper.seebug.org/994/

# Fastjson <= 1.2.24
TemplatesImpl
JdbcRowSetImpl

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
main:78, FastJsonLE1224


5._tfactory为什么为{}？
跟踪到这块的时候明明传入的是null, 结果进去就自动生成object了
com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, int)
排除debug本身的问题因为toString和上面那个选项都关掉了 有可能是代码有拦截器 -> FastjsonASMDeserializer_1_TransformerFactoryImpl

6.getOutputProperties()
fastjson-1.2.24-sources.jar!/com/alibaba/fastjson/parser/deserializer/JavaBeanDeserializer.java:599



