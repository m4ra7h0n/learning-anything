package com.xjjlearning.hack.java.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * created by xjj on 2023/1/15
 */

// java类的加载机制
// 参见《深入理解java虚拟机》第三部分 第七章
public class ReflectionTest {
    public static void main(String[] args) throws Exception{
        // 堆中的Class对象 作为程序访问元空间中类数据的外部接口
        Class<TestClass> testClass = TestClass.class;

        // 使用.class不会自动初始化对象
        // 但是使用forName会自动初始化对象
        Method[] methods = TestClass.class.getMethods();
        String name = TestClass.class.getName();
        int modifiers = TestClass.class.getModifiers();
        Arrays.stream(methods)
                .filter(m -> m.getName().equals("intToString"))
                .forEach(m -> {
                    System.out.println("go to intToString method..");
                    Class<?>[] parameterTypes = m.getParameterTypes();
                    Class<?> returnType = m.getReturnType();
                });

        // load inherit class, using the flag `$`
        Class<?> sub = Class.forName("com.xjjlearning.hack.java.reflection.TestClass$TestSub");
    }
}


