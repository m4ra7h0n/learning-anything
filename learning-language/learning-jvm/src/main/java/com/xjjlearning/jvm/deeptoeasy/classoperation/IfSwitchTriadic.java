package com.xjjlearning.jvm.deeptoeasy.classoperation;

/**
 * Created by xjj on 2023/3/4.
 */

import com.sun.org.apache.bcel.internal.classfile.LineNumberTable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;
import javassist.bytecode.StackMapTable;

/**
 * 学习条件分支的字节码
 */
public class IfSwitchTriadic {
    public int ifFunc(int type) {
        if (type == 1) {
            return 100;
        } else if (type == 2) {
            return 1000;
        } else {
            return 0;
        }
    }
    /*
     *   public int ifFunc(int);
     *     descriptor: (I)I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=2, args_size=2
     *       // 参数type的值放入操作数栈顶
     *          0: iload_1
     *          1: iconst_1
     *       // if_icmpne指令完成操作数栈顶两个整数的比较
     *       // 指令执行需要一个操作数，操作数是当前方法某条字节码指令的偏移量。当栈顶的两个int类型的元素不相等时，跳转到操作数指向的字节码指令
     *          2: if_icmpne     8
     *          5: bipush        100
     *       // 从当前方法返回int
     *          7: ireturn
     *          8: iload_1
     *          9: iconst_2
     *         10: if_icmpne     17
     *         13: sipush        1000
     *         16: ireturn
     *         17: iconst_0
     *         18: ireturn
     *       LineNumberTable:
     *         line 12: 0
     *         line 13: 5
     *         line 14: 8
     *         line 15: 13
     *         line 17: 17
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      19     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
     *             0      19     1  type   I
     *       StackMapTable: number_of_entries = 2
     *         frame_type = 8 /* same /
     *         frame_type = 8 /* same /
     *
     */

    /*
      使用goto
     */
    public int ifFunc2(int type) {
        if (type == 1) {
            type = 2;
        } else {
            type = 3;
        }
        return type;
    }

    public int switchFunc(int stat) {
        int a = 0;
        switch (stat) {
            case 5:
                a = 0;
                break;
            case 6:
            case 8:
                a = 1;
                break;
        }
        return a;
    }
    /*
     *   public int switchFunc(int);
     *     descriptor: (I)I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=3, args_size=2
     *          0: iconst_0
     *          1: istore_2
     *          2: iload_1
     *
     *         // 编译器会为case区间的每一个数字都生成一个case语句
     *          3: tableswitch   { // 5 to 8
     *                        5: 32
     *                        6: 37
     *                        7: 39
     *                        8: 37
     *                  default: 39
     *             }
     *         32: iconst_0
     *         33: istore_2
     *         34: goto          39
     *         37: iconst_1
     *         38: istore_2
     *         39: iload_2
     *         40: ireturn
     *       LineNumberTable:
     *         line 31: 0
     *         line 32: 2
     *         line 34: 32
     *         line 35: 34
     *         line 38: 37
     *         line 41: 39
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      41     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
     *             0      41     1  stat   I
     *             2      39     2     a   I
     *       StackMapTable: number_of_entries = 3
     *         frame_type = 252 /* append /
     *           offset_delta = 32
     *           locals = [ int ]
     *         frame_type = 4 /* same /
     *         frame_type = 1 /* same /
     *
     */

    public int switch2Func(int stat) {
        int a = 0;
        switch (stat) {
            case 1:
                a = 0;
                break;
            case 100:
                a = 1;
                break;
        }
        return a;
    }
    /*
     *   public int switch2Func(int);
     *     descriptor: (I)I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=3, args_size=2
     *          0: iconst_0
     *          1: istore_2
     *          2: iload_1
     *          3: lookupswitch  { // 2
     *                        1: 28
     *                      100: 33
     *                  default: 35
     *             }
     *         28: iconst_0
     *         29: istore_2
     *         30: goto          35
     *         33: iconst_1
     *         34: istore_2
     *         35: iload_2
     *         36: ireturn
     */

    public int syFunc(boolean sex) {
        return sex ? 1 : 0;
    }
    /*
     *   public int syFunc(boolean);
     *     descriptor: (Z)I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=2, args_size=2
     *          0: iload_1
     *        // 与0进行比较, (这里false == 0), 等于0就跳转
     *          1: ifeq          8
     *          4: iconst_1
     *          5: goto          9
     *          8: iconst_0
     *          9: ireturn
     *       LineNumberTable:
     *         line 58: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      10     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
     *             0      10     1   sex   Z
     *       StackMapTable: number_of_entries = 2
     *         frame_type = 8 /* same /
     *         frame_type = 64 /* same_locals_1_stack_item /
     *           stack = [ int ]
     *
     */

    public static void main(String[] args) {

    }
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.IfSwitchTriadic
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic.class
 *   Last modified 2023-3-4; size 1195 bytes
 *   MD5 checksum 5476ab96a153579144f05a8118828574
 *   Compiled from "IfSwitchTriadic.java"
 * public class com.xjjlearning.jvm.deeptoeasy.classoperation.IfSwitchTriadic
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #3.#31         // java/lang/Object."<init>":()V
 *    #2 = Class              #32            // com/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic
 *    #3 = Class              #33            // java/lang/Object
 *    #4 = Utf8               <init>
 *    #5 = Utf8               ()V
 *    #6 = Utf8               Code
 *    #7 = Utf8               LineNumberTable
 *    #8 = Utf8               LocalVariableTable
 *    #9 = Utf8               this
 *   #10 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *   #11 = Utf8               ifFunc
 *   #12 = Utf8               (I)I
 *   #13 = Utf8               type
 *   #14 = Utf8               I
 *   #15 = Utf8               StackMapTable
 *   #16 = Utf8               ifFunc2
 *   #17 = Utf8               switchFunc
 *   #18 = Utf8               stat
 *   #19 = Utf8               a
 *   #20 = Utf8               switch2Func
 *   #21 = Utf8               syFunc
 *   #22 = Utf8               (Z)I
 *   #23 = Utf8               sex
 *   #24 = Utf8               Z
 *   #25 = Utf8               main
 *   #26 = Utf8               ([Ljava/lang/String;)V
 *   #27 = Utf8               args
 *   #28 = Utf8               [Ljava/lang/String;
 *   #29 = Utf8               SourceFile
 *   #30 = Utf8               IfSwitchTriadic.java
 *   #31 = NameAndType        #4:#5          // "<init>":()V
 *   #32 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic
 *   #33 = Utf8               java/lang/Object
 * {
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.IfSwitchTriadic();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 10: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *
 *   public int ifFunc(int);
 *     descriptor: (I)I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: iload_1
 *          1: iconst_1
 *          2: if_icmpne     8
 *          5: bipush        100
 *          7: ireturn
 *          8: iload_1
 *          9: iconst_2
 *         10: if_icmpne     17
 *         13: sipush        1000
 *         16: ireturn
 *         17: iconst_0
 *         18: ireturn
 *       LineNumberTable:
 *         line 12: 0
 *         line 13: 5
 *         line 14: 8
 *         line 15: 13
 *         line 17: 17
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      19     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *             0      19     1  type   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 8 /* same /
 *         frame_type = 8 /* same /
 *
 *   public int ifFunc2(int);
 *     descriptor: (I)I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: iload_1
 *          1: iconst_1
 *          2: if_icmpne     10
 *          5: iconst_2
 *          6: istore_1
 *          7: goto          12
 *         10: iconst_3
 *         11: istore_1
 *         12: iload_1
 *         13: ireturn
 *       LineNumberTable:
 *         line 22: 0
 *         line 23: 5
 *         line 25: 10
 *         line 27: 12
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      14     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *             0      14     1  type   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 10 /* same /
 *         frame_type = 1 /* same /
 *
 *   public int switchFunc(int);
 *     descriptor: (I)I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=3, args_size=2
 *          0: iconst_0
 *          1: istore_2
 *          2: iload_1
 *          3: tableswitch   { // 5 to 8
 *                        5: 32
 *                        6: 37
 *                        7: 39
 *                        8: 37
 *                  default: 39
 *             }
 *         32: iconst_0
 *         33: istore_2
 *         34: goto          39
 *         37: iconst_1
 *         38: istore_2
 *         39: iload_2
 *         40: ireturn
 *       LineNumberTable:
 *         line 31: 0
 *         line 32: 2
 *         line 34: 32
 *         line 35: 34
 *         line 38: 37
 *         line 41: 39
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      41     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *             0      41     1  stat   I
 *             2      39     2     a   I
 *       StackMapTable: number_of_entries = 3
 *         frame_type = 252 /* append /
 *           offset_delta = 32
 *           locals = [ int ]
 *         frame_type = 4 /* same /
 *         frame_type = 1 /* same /
 *
 *   public int switch2Func(int);
 *     descriptor: (I)I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=3, args_size=2
 *          0: iconst_0
 *          1: istore_2
 *          2: iload_1
 *          3: lookupswitch  { // 2
 *                        1: 28
 *                      100: 33
 *                  default: 35
 *             }
 *         28: iconst_0
 *         29: istore_2
 *         30: goto          35
 *         33: iconst_1
 *         34: istore_2
 *         35: iload_2
 *         36: ireturn
 *       LineNumberTable:
 *         line 45: 0
 *         line 46: 2
 *         line 48: 28
 *         line 49: 30
 *         line 51: 33
 *         line 54: 35
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      37     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *             0      37     1  stat   I
 *             2      35     2     a   I
 *       StackMapTable: number_of_entries = 3
 *         frame_type = 252 /* append /
 *           offset_delta = 28
 *           locals = [ int ]
 *         frame_type = 4 /* same /
 *         frame_type = 1 /* same /
 *
 *   public int syFunc(boolean);
 *     descriptor: (Z)I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=2, args_size=2
 *          0: iload_1
 *          1: ifeq          8
 *          4: iconst_1
 *          5: goto          9
 *          8: iconst_0
 *          9: ireturn
 *       LineNumberTable:
 *         line 58: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      10     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/IfSwitchTriadic;
 *             0      10     1   sex   Z
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 8 /* same /
 *         frame_type = 64 /* same_locals_1_stack_item /
 *           stack = [ int ]
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 63: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  args   [Ljava/lang/String;
 * }
 * SourceFile: "IfSwitchTriadic.java"
 */