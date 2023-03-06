package com.xjjlearning.jvm.deeptoeasy.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassWriter extends ClassVisitor {

    private final ClassWriter classWriter;

    public MyClassWriter(ClassWriter classWriter) {
        super(Opcodes.ASM9, classWriter);
        this.classWriter = classWriter;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if ("deletedMethod".equals(name)) {
            return null;
        }
        // 在main的第一条命令前加入 打印 (hello world)
        if ("main".equals(name)) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MyMethodWriter(methodVisitor);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public byte[] toByteArray() {
        return classWriter.toByteArray();
    }
}