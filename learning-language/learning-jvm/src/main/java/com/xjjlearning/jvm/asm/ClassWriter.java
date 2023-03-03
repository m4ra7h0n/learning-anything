package com.xjjlearning.jvm.asm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClassWriter implements ClassVisitor {

    private int version;
    private String className;
    private String access;
    // 存储的是字段访问者
    private List<FieldWriter> fieldWriters = new ArrayList<>();
    // 存储的是方法访问者
    private List<MethodWriter> methodWriters = new ArrayList<>();

    @Override
    public void visit(int version, String access, String className) {
        this.version = version;
        this.className = className;
        this.access = access;
    }

    @Override
    public FieldVisitor visitField(String access, String name, String descriptor) {
        FieldWriter fieldWriter = new FieldWriter(access, name, descriptor);
        fieldWriters.add(fieldWriter);
        return fieldWriter;
    }

    @Override
    public MethodVisitor visitMethod(String access, String name, String descriptor) {
        MethodWriter methodWriter = new MethodWriter(access, name, descriptor);
        methodWriters.add(methodWriter);
        return methodWriter;
    }
}