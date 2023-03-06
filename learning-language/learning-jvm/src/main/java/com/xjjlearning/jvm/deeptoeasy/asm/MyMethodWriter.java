package com.xjjlearning.jvm.deeptoeasy.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

public class MyMethodWriter extends MethodVisitor {

    private final MethodVisitor methodVisitor;

    public MyMethodWriter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM6, methodVisitor);
        this.methodVisitor = methodVisitor;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        printString("hello word!");
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode == RETURN) {
            // 如果操作码等于return指令的操作码
            // 则在return指令之前插入一些字节码指令
            printString("hello word!");
        }
        super.visitInsn(opcode);
    }

    private void printString(String s) {
        methodVisitor.visitFieldInsn(
                GETSTATIC,
                Type.getInternalName(System.class),
                "out",
                Type.getDescriptor(System.out.getClass()));

        methodVisitor.visitLdcInsn(s);

        methodVisitor.visitMethodInsn(
                INVOKEVIRTUAL,
                Type.getInternalName(System.out.getClass()),
                "println",
                "(Ljava/lang/String;)V", false);
    }
}