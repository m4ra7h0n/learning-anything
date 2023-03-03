package com.xjjlearning.jvm.deeptoeasy;

/**
 * created by xjj on 2023/2/11
 */
public class Main {
    public static void main(String[] args) {
        // System.out.println(Thread.currentThread().getName());
        int a = 10;
        int result = ++a;
        System.out.println(result);
        /**
         *     Code:
         *       stack=2, locals=3, args_size=1
         *          0: bipush        10   -> 入栈<操作数栈>
         *          2: istore_1           -> 弹栈并存入<局部变量表>slot_1位置(赋值给变量a)
         *          3: iinc          1, 1 -> 将局部变量表第1个位置的变量自增1
         *          6: iload_1            -> 将自增后的a放入<操作数栈>slot_1
         *          7: istore_2           -> 弹栈并存入<局部变量表>slot_2位置(赋值给变量result)
         *          8: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         *         11: iload_2
         *         12: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
         *         15: return
         */
    }
}
