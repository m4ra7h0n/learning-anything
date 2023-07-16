https://tttang.com/archive/1405/#toc_snakeyaml
https://www.cnblogs.com/zpchcbd/p/14941783.html

# 简要理解
在高版本中（如：JDK8u191以上版本）虽然不能从远程加载恶意的Factory，但是我们依然可以在返回的Reference中指定Factory Class，这个工厂类必须在受害目标本地的CLASSPATH中。
工厂类必须实现 javax.naming.spi.ObjectFactory 接口，并且至少存在一个 getObjectInstance() 方法。
org.apache.naming.factory.BeanFactory 刚好满足条件并且存在被利用的可能。
org.apache.naming.factory.BeanFactory 存在于Tomcat依赖包中，所以使用也是非常广泛。

我们构造需要注册的reference是进行构造的, 其中工厂类指定为Tomcat, 资源类指定一些带有eval函数的类可以命令执行, 这些eval函数仅仅传入一个String类型的参数
```text
javax.naming.spi.NamingManager.getObjectInstance
                factory = getObjectFactoryFromReference(ref, f); // 从reference中拿到Tomcat
                if (factory != null) {
                    return factory.getObjectInstance(ref, name, nameCtx,
                                                     environment); // 执行tomcat的getObjectInstance()
                                                                   // 我们的reference也传入其中, 
                }
```
来到这个方法: org.apache.naming.factory.BeanFactory.getObjectInstance()
其中将我们指定的资源类实例化成一个bean
然后我们遍历reference, 找到key为forceString的, 将value变为相应的setter
最后使用这个bean来执行我们的强制转换的setter来为变量赋值, 传入的参数只能是一个且为String类型
这个时候setter 可以指定为eval函数, 然后传入的参数是恶意的代码

然后我们来找一些类来充当资源类
1.javax.el.ELProcessor#eval
2.groovy.lang.GroovyShell#evaluate


# 问题
## 1.不知道js怎么执行的java代码?
```text
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // 执行javascript
        engine.eval("print('hello word!!')");
        engine.eval("print(\"Visit Runoob!\".search(/Runoob/i))");
        engine.eval("new java.lang.ProcessBuilder['(java.lang.String[])']" +
                "(['open', '/System/Applications/Calculator.app']).start()");
```