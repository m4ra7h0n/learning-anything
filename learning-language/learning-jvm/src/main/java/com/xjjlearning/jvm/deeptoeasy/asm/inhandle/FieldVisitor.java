package com.xjjlearning.jvm.deeptoeasy.asm.inhandle;

public interface FieldVisitor {
    // 为字段添加一个注解
    void visitAnnotation(String annotation, boolean runtime);
}