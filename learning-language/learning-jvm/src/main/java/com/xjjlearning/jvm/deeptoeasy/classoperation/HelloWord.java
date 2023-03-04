package com.xjjlearning.jvm.deeptoeasy.classoperation;

public class HelloWord {
    public static void main(String[] args) {  
        System.out.println("Hello Word");  
    }
    /**
     *   public com.xjjlearning.jvm.deeptoeasy.codeoperation.HelloWord();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=1, args_size=1
     *          // 字节码指令的前面都会标有数字，这些数字是每条指令在Code属性中code[]数组的索引
     *          // 在需要操作数的指令后面会存储该指令执行所需的操作数，所以指令前面的数字不是连续的
     *          0: aload_0
     *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *          4: return
     *       LineNumberTable:
     *         line 3: 0
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/HelloWord;
     *
     *   public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=1, args_size=1
     *          // getstatic，对应的操作码是0xB2，该指令需要一个操作数(#2)，该操作数必须是常量池中某个CONSTANT_Fieldref_info常量的索引
     *          // 该指令执行完成后，操作数栈顶存放的就是System的out静态字段的引用
     *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *
     *          // ldc，对应的操作码是0x12，该指令也需要一个操作数，值为常量池中的某个CONSTANT_String_info常量的索引
     *          // 其作用是将常量池中的“Hello Word”字符串的引用放入操作数栈顶
     *          3: ldc           #3                  // String Hello Word
     *
     *          // invokevirtual，对应的操作码是0xB6，该指令也需要一个操作数，值为常量池中某个CONSTANT_Methodref_info常量的索引
     *          // 它的作用是调用PrintStream对象的println方法
     *          // invokevirtual指令执行之前，操作数栈必须存在一个System.out对象的引用，和println方法所需的参数，并且顺序是严格要求的，正是前面getstatic、ldc两条指令执行的结果
     *          5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *
     *          8: return
     *       LineNumberTable:
     *         line 5: 0
     *         line 6: 8
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0       9     0  args   [Ljava/lang/String;
     *
     */
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.codeoperation.HelloWord
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/HelloWord.class
 *   Last modified 2023-3-4; size 591 bytes
 *   MD5 checksum a0b25dbc86ddd1533271b12835dacc69
 *   Compiled from "HelloWord.java"
 * public class com.xjjlearning.jvm.deeptoeasy.codeoperation.HelloWord
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #6.#20         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #21.#22        // java/lang/System.out:Ljava/io/PrintStream;
 *    #3 = String             #23            // Hello Word
 *    #4 = Methodref          #24.#25        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #5 = Class              #26            // com/xjjlearning/jvm/deeptoeasy/HelloWord
 *    #6 = Class              #27            // java/lang/Object
 *    #7 = Utf8               <init>
 *    #8 = Utf8               ()V
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               LocalVariableTable
 *   #12 = Utf8               this
 *   #13 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/HelloWord;
 *   #14 = Utf8               main
 *   #15 = Utf8               ([Ljava/lang/String;)V
 *   #16 = Utf8               args
 *   #17 = Utf8               [Ljava/lang/String;
 *   #18 = Utf8               SourceFile
 *   #19 = Utf8               HelloWord.java
 *   #20 = NameAndType        #7:#8          // "<init>":()V
 *   #21 = Class              #28            // java/lang/System
 *   #22 = NameAndType        #29:#30        // out:Ljava/io/PrintStream;
 *   #23 = Utf8               Hello Word
 *   #24 = Class              #31            // java/io/PrintStream
 *   #25 = NameAndType        #32:#33        // println:(Ljava/lang/String;)V
 *   #26 = Utf8               com/xjjlearning/jvm/deeptoeasy/HelloWord
 *   #27 = Utf8               java/lang/Object
 *   #28 = Utf8               java/lang/System
 *   #29 = Utf8               out
 *   #30 = Utf8               Ljava/io/PrintStream;
 *   #31 = Utf8               java/io/PrintStream
 *   #32 = Utf8               println
 *   #33 = Utf8               (Ljava/lang/String;)V
 * {
 *   public com.xjjlearning.jvm.deeptoeasy.codeoperation.HelloWord();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 3: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/HelloWord;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *          3: ldc           #3                  // String Hello Word
 *          5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *          8: return
 *       LineNumberTable:
 *         line 5: 0
 *         line 6: 8
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       9     0  args   [Ljava/lang/String;
 * }
 * SourceFile: "HelloWord.java"
 */