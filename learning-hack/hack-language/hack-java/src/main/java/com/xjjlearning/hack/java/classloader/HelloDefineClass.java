package com.xjjlearning.hack.java.classloader;

import com.xjjlearning.hack.java.classloader.bytecodes.EvilTemplatesImpl;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created by xjj on 2023/1/30
 */
public class HelloDefineClass {

    static class AClassLoader extends ClassLoader { }

    public static void main(String[] args) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        defineClass.setAccessible(true);

        // javac EvilTemplatesImpl.java; cat EvilTemplatesImpl.class | base64
        // String codeString = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEACmhlbGxvLCB4amoHABkMABoAGwEACWNvbS9IZWxsbwEAEGphdmEvbGFuZy9PYmplY3QBABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYAIQAFAAYAAAAAAAIAAQAHAAgAAQAJAAAAHQABAAEAAAAFKrcAAbEAAAABAAoAAAAGAAEAAAADAAgACwAIAAEACQAAACUAAgAAAAAACbIAAhIDtgAEsQAAAAEACgAAAAoAAgAAAAUACAAGAAEADAAAAAIADQ==";
        // byte[] code = Base64.getDecoder().decode(codeString);

        // 不会执行static代码块
        byte[] code = ClassUtil.classAsBytes(EvilTemplatesImpl.class);

        // 前面已经加载过一次了, 不会重复加载这里需要一个新的类加载器
        // String name, byte[] b, int off, int len
        Class<?> hello = (Class<?>) defineClass.invoke(new AClassLoader(), code, 0, code.length);
        hello.newInstance();
    }
}
