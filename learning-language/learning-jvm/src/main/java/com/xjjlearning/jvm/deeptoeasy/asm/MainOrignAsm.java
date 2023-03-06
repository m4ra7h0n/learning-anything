package com.xjjlearning.jvm.deeptoeasy.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by xjj on 2023/3/6.
 */

/**
 * 使用原生asm生成类
 */
public class MainOrignAsm {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String className = "com.xjjlearning.jvm.deeptoeasy.asm.AsmGenerateClass";
        String signature = "L" + className.replace(".", "/") + ";";
        // 字节计算局部变量表和操作数栈大小、栈映射桢
        ClassWriter classWriter = new ClassWriter(0);

        generateClass(classWriter, className, signature);
        generateMethod(classWriter);
        generateField(classWriter);

        // 获取生成的class的字节数组
        byte[] byteCode = classWriter.toByteArray();
        ByteCodeUtils.savaToFile(className, byteCode);

        // 最后加载我们生成的类
        Class<?> clazz = MainOrignAsm.class.getClassLoader().loadClass(className);
        System.out.println(clazz.getName());
    }

    static void generateClass(ClassWriter classWriter, String className, String signature) {
        /* 生成一个空的class
         * 类名和父类名需要使用 “/”替换“.”。这个可以在常量池中找到答案
         * version：指定类文件结构的版本号；
         * access：指定类的访问标志，如public、final等；
         * name：指定类的名称（内部类名），如“java/lang/String”；
         * signature：类的类型签名，如“Ljava/lang/String;”；
         * superName：继承的父类名称。除Object类外，所有的类都必须有父类；
         * interfaces：该类需要实现的接口。
         */
        classWriter.visit(Opcodes.V1_8, ACC_PUBLIC,
                className.replace(".", "/"),
                signature,
                Object.class.getName().replace(".", "/"),
                null);
        classWriter.visitEnd();
    }

    /**
     * 生成构造方法并调用父类<init>方法的字节码指令
     */
    static void generateMethod(ClassWriter classWriter) {
        /*
         * 生成构造方法
         * access：方法的访问标志，如public、static等；
         * name：方法的名称（内部类名）；
         * descriptor：方法的描述符，如“()V”；
         * signature：方法签名(参数+返回值)，可以为空； JVM可以允许一个类中方法名和参数相同，但返回值不同的方法同时存在。
         * exceptions：该方法可能抛出的受检异常的类型内部名称，可以为空。
         */
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();

        /* 调用父类构造器 */

        // 往Code属性的code数组中添加一条需要一个操作数的字节码指令
        methodVisitor.visitVarInsn(ALOAD, 0);
        // 往Code属性的code数组中添加一条调用方法的字节码指令
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false); // Object <init>()V
        // 往Code属性的code数组中添加一条无操作数的字节码指令
        methodVisitor.visitInsn(RETURN);
        // 设置操作数栈和局部变量表大小
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    /**
     * 生成字段
     */
    static void generateField(ClassWriter classWriter) {
        FieldVisitor fieldVisitor = classWriter.visitField(
                ACC_PUBLIC | ACC_STATIC | ACC_FINAL,
                "age",
                "I",
                null, 100);

        FieldVisitor fieldVisitorPrivate = classWriter.visitField(
                ACC_PRIVATE,
                "name",
                "Ljava/lang/String;",
                null, null);

        fieldVisitorPrivate.visitAnnotation("Llombok/Getter;", true);
    }
}
