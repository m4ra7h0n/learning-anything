package com.xjjlearning.jvm.deeptoeasy.loadclass;

import java.net.URLClassLoader;

/**
 * Created by xjj on 2023/3/5.
 */
public class ClassLoaderMain {
    private final static String className = "com.xjjlearning.jvm.deeptoeasy.loadclass.ClassLoaderTest";
    public static void loadClass1() throws ClassNotFoundException {
        // 不执行static
        /*
         * URLClassLoader.defineClass()
         *  ...
         *   defineClass1()
         *    jdk/src/share/native/java/lang/ClassLoader.c/Java_java_lang_ClassLoader_defineClass1() -> JNI
         *     hotspot/src/share/vm/prims/jvm.cpp/JVM_ENTRY(jclass, JVM_DefineClassWithSource())
         *      hotspot/src/share/vm/prims/jvm.cpp/jvm_define_class_common()
         *       1.resolve_from_stream() -> 解析class文件字节流生成c++Object存放于元空间, 完成类加载的加载阶段
         *       2.JNIHandles::make_local
         */
        Class<?> clazz = ClassLoaderMain.class.getClassLoader().loadClass(className);
        System.out.println(clazz.getName());
    }
    public static void loadClass2() throws ClassNotFoundException {
        // 执行static
        // ClassLoaderTest的静态代码块被调用了，而静态代码块是被编译器编译后放入类的初始化方法<clinit>中的
        /**
         * forName()
         *  forName0()
         *   jdk/src/share/native/java/lang/Class.c/Java_java_lang_Class_forName0() -> JNI
         *    hotspot/src/share/vm/prims/jvm.cpp/JVM_ENTRY(jclass, JVM_FindClassFromCaller())
         *     hotspot/src/share/vm/prims/jvm.cpp/find_class_from_class_loader()
         *      1.resolve_or_fail -> 解析class文件字节流生成c++Object存放于元空间, 完成类加载的加载阶段
         *      2.klass_handle->initialize -> 类的初始化
         *      3.JNIHandles::make_local
         */
        Class<?> clazz = Class.forName(className);
        System.out.println(clazz.getName());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        loadClass1();
//        loadClass2();
    }
    /**
     * 其余部分源码
     * hotspot/src/share/vm/classfile/classFileParser.cpp -> 验证
     *   hotspot/src/share/vm/classfile/systemDirectory.cpp/define_instance_class()
     *   hotspot/src/share/vm/classfile/systemDirectory.cpp/find_or_define_instance_class()
     *     hotspot/src/share/vm/classfile/systemDirectory.cpp/define_instance_class()
     *       // 1.定位index 2.记录class实例 3.eager_initialize()
     *       hotspot/src/share/vm/oops/instanceKlass.cpp/eager_initialize() -> 想要完成链接阶段，包括验证阶段的字节码验证行为，但此时并不会执行，实际执行是在类初始化之前。
     *         hotspot/src/share/vm/oops/instanceKlass.cpp/eager_initialize_impl()
     *           hotspot/src/share/vm/oops/instanceKlass.cpp/link_class_impl() -> 实际链接阶段
     *           // 1.递归链接父类, 是接口抛出异常
     *           // 2.链接该类实现的所有接口
     *           // 3.验证字节码
     *             verify_code()
     *               Verifier::verify->Verifier::verify_class->ClassVerifier::verify_class->ClassVerifier::verify_method
     *                 verify_method方法中包括验证方法签名、异常处理表、局部变量表、栈映射桢、字节码指令
     */

}
