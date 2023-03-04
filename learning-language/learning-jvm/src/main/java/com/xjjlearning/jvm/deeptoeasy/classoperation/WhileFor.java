package com.xjjlearning.jvm.deeptoeasy.classoperation;

import java.util.List;

/**
 * Created by xjj on 2023/3/4.
 */
public class WhileFor {
    public void whileDemo() {
        int count = 10;
        while (count > 0) {
            count--;
        }
    }
    /*
     *   public void whileDemo();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=2, args_size=1
     *          0: bipush        10
     *          2: istore_1
     *          3: iload_1
     *          4: ifle          13
     *        // iinc指令是操作localValue (Slot_1, -1)
     *          7: iinc          1, -1
     *         10: goto          3
     *         13: return
     *       LineNumberTable:
     *         line 10: 0
     *         line 11: 3
     *         line 12: 7
     *         line 14: 13
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      14     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
     *             3      11     1 count   I
     */

    public int forDemo() {
        int count = 0;
        for (int i = 1; i <= 10; i++) {
            count += i;
        }
        return count;
    }
    /*
     *   public int forDemo();
     *     descriptor: ()I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=3, args_size=1
     *          0: iconst_0
     *          1: istore_1
     *          2: iconst_1
     *          3: istore_2
     *          4: iload_2
     *          5: bipush        10
     *          7: if_icmpgt     20
     *         10: iload_1
     *         11: iload_2
     *         12: iadd
     *         13: istore_1
     *         14: iinc          2, 1
     *         17: goto          4
     *         20: iload_1
     *         21: ireturn
     *       LineNumberTable:
     *         line 17: 0
     *         line 18: 2
     *         line 19: 10
     *         line 18: 14
     *         line 21: 20
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             4      16     2     i   I
     *             0      22     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
     *             2      20     1 count   I
     */

    public void forEachDemo(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }
    /*
     *   public void forEachDemo(java.util.List<java.lang.String>);
     *     descriptor: (Ljava/util/List;)V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=4, args_size=2
     *          0: aload_1
     *          1: invokeinterface #2,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
     *          6: astore_2
     *          7: aload_2
     *          8: invokeinterface #3,  1            // InterfaceMethod java/util/Iterator.hasNext:()Z
     *         13: ifeq          36
     *         16: aload_2
     *         17: invokeinterface #4,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
     *         22: checkcast     #5                  // class java/lang/String
     *         25: astore_3
     *
     *         26: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *         29: aload_3
     *         30: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         33: goto          7
     *         36: return
     *       LineNumberTable:
     *         line 25: 0
     *         line 26: 26
     *         line 27: 33
     *         line 28: 36
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *            26       7     3   str   Ljava/lang/String;
     *             0      37     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
     *             0      37     1  list   Ljava/util/List;
     *       LocalVariableTypeTable:
     *         Start  Length  Slot  Name   Signature
     *             0      37     1  list   Ljava/util/List<Ljava/lang/String;>;
     */

    public static void main(String[] args) {

    }
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.WhileFor
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor.class
 *   Last modified 2023-3-4; size 1441 bytes
 *   MD5 checksum c0854eb42792412490c006efe20eecc3
 *   Compiled from "WhileFor.java"
 * public class com.xjjlearning.jvm.deeptoeasy.classoperation.WhileFor
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #9.#41         // java/lang/Object."<init>":()V
 *    #2 = InterfaceMethodref #42.#43        // java/util/List.iterator:()Ljava/util/Iterator;
 *    #3 = InterfaceMethodref #44.#45        // java/util/Iterator.hasNext:()Z
 *    #4 = InterfaceMethodref #44.#46        // java/util/Iterator.next:()Ljava/lang/Object;
 *    #5 = Class              #47            // java/lang/String
 *    #6 = Fieldref           #48.#49        // java/lang/System.out:Ljava/io/PrintStream;
 *    #7 = Methodref          #50.#51        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #8 = Class              #52            // com/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor
 *    #9 = Class              #53            // java/lang/Object
 *   #10 = Utf8               <init>
 *   #11 = Utf8               ()V
 *   #12 = Utf8               Code
 *   #13 = Utf8               LineNumberTable
 *   #14 = Utf8               LocalVariableTable
 *   #15 = Utf8               this
 *   #16 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
 *   #17 = Utf8               whileDemo
 *   #18 = Utf8               count
 *   #19 = Utf8               I
 *   #20 = Utf8               StackMapTable
 *   #21 = Utf8               forDemo
 *   #22 = Utf8               ()I
 *   #23 = Utf8               i
 *   #24 = Utf8               forEachDemo
 *   #25 = Utf8               (Ljava/util/List;)V
 *   #26 = Utf8               str
 *   #27 = Utf8               Ljava/lang/String;
 *   #28 = Utf8               list
 *   #29 = Utf8               Ljava/util/List;
 *   #30 = Utf8               LocalVariableTypeTable
 *   #31 = Utf8               Ljava/util/List<Ljava/lang/String;>;
 *   #32 = Class              #54            // java/util/Iterator
 *   #33 = Utf8               Signature
 *   #34 = Utf8               (Ljava/util/List<Ljava/lang/String;>;)V
 *   #35 = Utf8               main
 *   #36 = Utf8               ([Ljava/lang/String;)V
 *   #37 = Utf8               args
 *   #38 = Utf8               [Ljava/lang/String;
 *   #39 = Utf8               SourceFile
 *   #40 = Utf8               WhileFor.java
 *   #41 = NameAndType        #10:#11        // "<init>":()V
 *   #42 = Class              #55            // java/util/List
 *   #43 = NameAndType        #56:#57        // iterator:()Ljava/util/Iterator;
 *   #44 = Class              #54            // java/util/Iterator
 *   #45 = NameAndType        #58:#59        // hasNext:()Z
 *   #46 = NameAndType        #60:#61        // next:()Ljava/lang/Object;
 *   #47 = Utf8               java/lang/String
 *   #48 = Class              #62            // java/lang/System
 *   #49 = NameAndType        #63:#64        // out:Ljava/io/PrintStream;
 *   #50 = Class              #65            // java/io/PrintStream
 *   #51 = NameAndType        #66:#67        // println:(Ljava/lang/String;)V
 *   #52 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor
 *   #53 = Utf8               java/lang/Object
 *   #54 = Utf8               java/util/Iterator
 *   #55 = Utf8               java/util/List
 *   #56 = Utf8               iterator
 *   #57 = Utf8               ()Ljava/util/Iterator;
 *   #58 = Utf8               hasNext
 *   #59 = Utf8               ()Z
 *   #60 = Utf8               next
 *   #61 = Utf8               ()Ljava/lang/Object;
 *   #62 = Utf8               java/lang/System
 *   #63 = Utf8               out
 *   #64 = Utf8               Ljava/io/PrintStream;
 *   #65 = Utf8               java/io/PrintStream
 *   #66 = Utf8               println
 *   #67 = Utf8               (Ljava/lang/String;)V
 * {
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.WhileFor();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 8: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
 *
 *   public void whileDemo();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=2, args_size=1
 *          0: bipush        10
 *          2: istore_1
 *          3: iload_1
 *          4: ifle          13
 *          7: iinc          1, -1
 *         10: goto          3
 *         13: return
 *       LineNumberTable:
 *         line 10: 0
 *         line 11: 3
 *         line 12: 7
 *         line 14: 13
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      14     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
 *             3      11     1 count   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 252 /* append /
 *           offset_delta = 3
 *           locals = [ int ]
 *         frame_type = 9 /* same /
 *
 *   public int forDemo();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: iconst_0
 *          1: istore_1
 *          2: iconst_1
 *          3: istore_2
 *          4: iload_2
 *          5: bipush        10
 *          7: if_icmpgt     20
 *         10: iload_1
 *         11: iload_2
 *         12: iadd
 *         13: istore_1
 *         14: iinc          2, 1
 *         17: goto          4
 *         20: iload_1
 *         21: ireturn
 *       LineNumberTable:
 *         line 17: 0
 *         line 18: 2
 *         line 19: 10
 *         line 18: 14
 *         line 21: 20
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             4      16     2     i   I
 *             0      22     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
 *             2      20     1 count   I
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 253 /* append /
 *           offset_delta = 4
 *           locals = [ int, int ]
 *         frame_type = 250 /* chop /
 *           offset_delta = 15
 *
 *   public void forEachDemo(java.util.List<java.lang.String>);
 *     descriptor: (Ljava/util/List;)V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=4, args_size=2
 *          0: aload_1
 *          1: invokeinterface #2,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
 *          6: astore_2
 *          7: aload_2
 *          8: invokeinterface #3,  1            // InterfaceMethod java/util/Iterator.hasNext:()Z
 *         13: ifeq          36
 *         16: aload_2
 *         17: invokeinterface #4,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
 *         22: checkcast     #5                  // class java/lang/String
 *         25: astore_3
 *         26: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         29: aload_3
 *         30: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         33: goto          7
 *         36: return
 *       LineNumberTable:
 *         line 25: 0
 *         line 26: 26
 *         line 27: 33
 *         line 28: 36
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *            26       7     3   str   Ljava/lang/String;
 *             0      37     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/WhileFor;
 *             0      37     1  list   Ljava/util/List;
 *       LocalVariableTypeTable:
 *         Start  Length  Slot  Name   Signature
 *             0      37     1  list   Ljava/util/List<Ljava/lang/String;>;
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 252 /* append /
 *           offset_delta = 7
 *           locals = [ class java/util/Iterator ]
 *         frame_type = 250 /* chop /
 *           offset_delta = 28
 *     Signature: #34                          // (Ljava/util/List<Ljava/lang/String;>;)V
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 32: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  args   [Ljava/lang/String;
 * }
 * SourceFile: "WhileFor.java"
 */