package com.xjjlearning.jvm.deeptoeasy.asm.inhandle;

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

    public void showClass() {

        System.out.println("版本号：" + getVersion());
        System.out.println("访问标志：" + getAccess());
        System.out.println("类名：" + getClassName());

        fieldWriters.stream()
                .map(f -> {
                            String annotations = f.getAnnotations().stream()
                                    .reduce(" ", String::join);
                            return f.getAccess() + " " +
                                    f.getDescriptor() + " " +
                                    f.getName() + " " + annotations;
                        }
                )
                .forEach(System.out::println);

        methodWriters.stream()
                .map(m -> m.getAccess() + " " +
                        m.getName() +
                        m.getDescriptor() + " " +
                        "操作数栈大小: " + m.getMaxStackSize() + " " +
                        "局部变量表大小: " + m.getMaxLocalSize())
                .forEach(System.out::println);
    }

}