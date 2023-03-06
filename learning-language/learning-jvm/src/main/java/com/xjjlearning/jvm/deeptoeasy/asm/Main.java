package com.xjjlearning.jvm.deeptoeasy.asm;

import com.xjjlearning.jvm.deeptoeasy.asm.inhandle.ClassWriter;
import com.xjjlearning.jvm.deeptoeasy.asm.inhandle.FieldVisitor;
import com.xjjlearning.jvm.deeptoeasy.asm.inhandle.MethodVisitor;

/**
 * Created by xjj on 2023/3/6.
 */
public class Main {
    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(52, "public", "com.xjjlearning.User");
        // 添加字段
        FieldVisitor fieldVisitor = classWriter
                .visitField("private", "name", "Ljava/lang/String;");
        // 为字段添加注解
        fieldVisitor.visitAnnotation("@Getter", true);
        // 添加方法
        MethodVisitor methodVisitor = classWriter
                .visitMethod("public", "getName", "(Ljava/lang/String)V");
        // 设置局部变量表和操作数栈的大小
        methodVisitor.visitMaxs(1, 1);
        classWriter.showClass();
    }
}
