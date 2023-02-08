package com.xjjlearning.jvm.asm;

public interface MethodVisitor {
    // 设置局部变量表和操作数栈的大小
    void visitMaxs(int maxStackSize, int maxLocalSize);
}