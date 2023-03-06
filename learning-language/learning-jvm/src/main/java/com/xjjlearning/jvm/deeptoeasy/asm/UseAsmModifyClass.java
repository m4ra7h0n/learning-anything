package com.xjjlearning.jvm.deeptoeasy.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;

/*
 * Created by xjj on 2023/3/6.
 */

/**
 * 1.给本类加一个带有getter的字段
 * 2.移除掉deletedMethod函数
 * 3.在main第一行添加一个 System.out.println("Hello World");
 * 4.在main最后一行添加一个 System.out.println("Hello World");
 */
public class UseAsmModifyClass {
    public static void main(String[] args) throws IOException {
        String className = "com.xjjlearning.jvm.deeptoeasy.asm.UseAsmModifyClass";
        ClassReader classReader = new ClassReader(className);

        // ClassWriter classWriter = new ClassWriter(0);
        MyClassWriter classWriter = new MyClassWriter(new ClassWriter(0));

        // 由该ClassVisitor实例访问ClassReader实例解析后的class字节数组
        classReader.accept(classWriter, 0);

        generateField(classWriter);

        byte[] newByteCode = classWriter.toByteArray();
        ByteCodeUtils.savaToFile(className, newByteCode);
    }

    static void generateField(ClassWriter classWriter) {
        FieldVisitor fieldVisitor = classWriter.visitField(
                ACC_PRIVATE,
                "name",
                "Ljava/lang/String;",
                null, null);

        fieldVisitor.visitAnnotation("Llombok/Getter;", false);
    }

    static void generateField(MyClassWriter classWriter) {
        FieldVisitor fieldVisitor = classWriter.visitField(
                ACC_PRIVATE,
                "name",
                "Ljava/lang/String;",
                null, null);

        fieldVisitor.visitAnnotation("Llombok/Getter;", false);
    }

    static void deletedMethod() {}
}
