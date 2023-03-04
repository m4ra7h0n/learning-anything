package com.xjjlearning.jvm.deeptoeasy.classoperation;

/**
 * Created by xjj on 2023/3/4.
 */

/**
 * 关于操作数栈和局部变量表的基础
 */
public class OpStackVarTable {
    public static void main(String[] args) {
        int a = 10, b = 20;
        int c = b;
        b = a;
    }
    /**
     * Code:
     *   stack=1, locals=4, args_size=1
     *     // 将一个立即数10入栈到操作数栈顶
     *     // 如果立即数大于等于-1且小于等于5，可使用对应的iconst_xx指令，如果立即数超过5，只能使用bipush指令
     *      0: bipush        10
     *     // 将当前操作数栈顶的元素弹栈并存储到局部变量表索引为1的Slot
     *      2: istore_1
     *      3: bipush        20
     *      5: istore_2
     *     // 将局部变量表索引为2的元素放入操作数栈的栈顶
     *      6: iload_2
     *      7: istore_3
     *      8: iload_1
     *      9: istore_2
     *     10: return
     *
     *  // 通过这个表我们知道某个位置存储的是局部变量a、b、还是c
     *   LocalVariableTable:
     *     Start  Length  Slot  Name   Signature
     *     // 通常start指向变量第一次被赋值的字节码指令, 方法参数 start = 0
     *     // 似乎所有变量在方法结束之前都是有效的, 总和就是整个字节码指令的长度
     *     // 如果一个块中存放, length会比较短
     *         0      11     0  args   [Ljava/lang/String;
     *         3       8     1     a   I
     *         6       5     2     b   I
     *         8       3     3     c   I
     */
}

/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.codeoperation.OpStackVarTableTest
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/OpStackVarTableTest.class
 * Last modified 2023-3-4; size 540 bytes
 * MD5 checksum 45a403307ebdd0a50ec496095434e72b
 * Compiled from "OpStackVarTableTest.java"
 * public class com.xjjlearning.jvm.deeptoeasy.codeoperation.OpStackVarTableTest
 * minor version: 0
 * major version: 52
 * flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 * #1 = Methodref          #3.#21         // java/lang/Object."<init>":()V
 * #2 = Class              #22            // com/xjjlearning/jvm/deeptoeasy/test/OpStackVarTableTest
 * #3 = Class              #23            // java/lang/Object
 * #4 = Utf8               <init>
 * #5 = Utf8               ()V
 * #6 = Utf8               Code
 * #7 = Utf8               LineNumberTable
 * #8 = Utf8               LocalVariableTable
 * #9 = Utf8               this
 * #10 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/test/OpStackVarTableTest;
 * #11 = Utf8               main
 * #12 = Utf8               ([Ljava/lang/String;)V
 * #13 = Utf8               args
 * #14 = Utf8               [Ljava/lang/String;
 * #15 = Utf8               a
 * #16 = Utf8               I
 * #17 = Utf8               b
 * #18 = Utf8               c
 * #19 = Utf8               SourceFile
 * #20 = Utf8               OpStackVarTableTest.java
 * #21 = NameAndType        #4:#5          // "<init>":()V
 * #22 = Utf8               com/xjjlearning/jvm/deeptoeasy/test/OpStackVarTableTest
 * #23 = Utf8               java/lang/Object
 * {
 * public com.xjjlearning.jvm.deeptoeasy.codeoperation.OpStackVarTableTest();
 * descriptor: ()V
 * flags: ACC_PUBLIC
 * Code:
 * stack=1, locals=1, args_size=1
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 * LineNumberTable:
 * line 6: 0
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/test/OpStackVarTableTest;
 * <p>
 * public static void main(java.lang.String[]);
 * descriptor: ([Ljava/lang/String;)V
 * flags: ACC_PUBLIC, ACC_STATIC
 * Code:
 * stack=1, locals=4, args_size=1
 * 0: bipush        10
 * 2: istore_1
 * 3: bipush        20
 * 5: istore_2
 * 6: iload_2
 * 7: istore_3
 * 8: iload_1
 * 9: istore_2
 * 10: return
 * LineNumberTable:
 * line 8: 0
 * line 9: 6
 * line 10: 8
 * line 11: 10
 * LocalVariableTable:
 * Start  Length  Slot  Name   Signature
 * 0      11     0  args   [Ljava/lang/String;
 * 3       8     1     a   I
 * 6       5     2     b   I
 * 8       3     3     c   I
 * }
 * SourceFile: "OpStackVarTableTest.java"
 */