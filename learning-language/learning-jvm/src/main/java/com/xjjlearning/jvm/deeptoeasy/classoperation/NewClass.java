package com.xjjlearning.jvm.deeptoeasy.classoperation;

import com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User;

/**
 * Created by xjj on 2023/3/4.
 */

/**
 * 关于new class的字节码层面解读
 */
public class NewClass {
    public static void main(String[] args) {
        UserService service = new UserService();
        User user = service.getUser();
        String name = user.getName();
        System.out.println(name);
    }

    /**
     *   public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=4, args_size=1
     *         // 创建一个UserService类型的对象，并且指令的返回值为对象的引用，保存在操作数栈的栈顶
     *          0: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService
     *         // 复制当前栈顶一个Slot的数据，复制后的数据存储在操作数栈的栈顶(初始化会消耗掉一个, 复制为了保留一个引用以便以后使用 -> LocalVariableTable Slot_1)
     *          3: dup
     *         // 调用实例构造器方法，私有方法和父类方法
     *          4: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService."<init>":()V
     *          7: astore_1
     *
     *         // service.getUser()
     *          8: aload_1
     *          9: invokevirtual #4                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService.getUser:()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     *         12: astore_2
     *
     *         // user.getName()
     *         13: aload_2
     *         14: invokevirtual #5                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User.getName:()Ljava/lang/String;
     *         17: astore_3
     *
     *         // sout(name)
     *         18: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *         21: aload_3
     *         22: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         25: return
     *       LineNumberTable:
     *         line 12: 0
     *         line 13: 8
     *         line 14: 13
     *         line 15: 18
     *         line 16: 25
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      26     0  args   [Ljava/lang/String;
     *             8      18     1 service   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService;
     *            13      13     2  user   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     *            18       8     3  name   Ljava/lang/String;
     */
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.NewClassMain
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/NewClassMain.class
 *   Last modified 2023-3-4; size 1114 bytes
 *   MD5 checksum 50369af76d2b875e30233e69cb1796c9
 *   Compiled from "NewClassMain.java"
 * public class com.xjjlearning.jvm.deeptoeasy.classoperation.NewClassMain
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #9.#29         // java/lang/Object."<init>":()V
 *    #2 = Class              #30            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService
 *    #3 = Methodref          #2.#29         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService."<init>":()V
 *    #4 = Methodref          #2.#31         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService.getUser:()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *    #5 = Methodref          #32.#33        // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User.getName:()Ljava/lang/String;
 *    #6 = Fieldref           #34.#35        // java/lang/System.out:Ljava/io/PrintStream;
 *    #7 = Methodref          #36.#37        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #8 = Class              #38            // com/xjjlearning/jvm/deeptoeasy/classoperation/NewClassMain
 *    #9 = Class              #39            // java/lang/Object
 *   #10 = Utf8               <init>
 *   #11 = Utf8               ()V
 *   #12 = Utf8               Code
 *   #13 = Utf8               LineNumberTable
 *   #14 = Utf8               LocalVariableTable
 *   #15 = Utf8               this
 *   #16 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/NewClassMain;
 *   #17 = Utf8               main
 *   #18 = Utf8               ([Ljava/lang/String;)V
 *   #19 = Utf8               args
 *   #20 = Utf8               [Ljava/lang/String;
 *   #21 = Utf8               service
 *   #22 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService;
 *   #23 = Utf8               user
 *   #24 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #25 = Utf8               name
 *   #26 = Utf8               Ljava/lang/String;
 *   #27 = Utf8               SourceFile
 *   #28 = Utf8               NewClassMain.java
 *   #29 = NameAndType        #10:#11        // "<init>":()V
 *   #30 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService
 *   #31 = NameAndType        #40:#41        // getUser:()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #32 = Class              #42            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
 *   #33 = NameAndType        #43:#44        // getName:()Ljava/lang/String;
 *   #34 = Class              #45            // java/lang/System
 *   #35 = NameAndType        #46:#47        // out:Ljava/io/PrintStream;
 *   #36 = Class              #48            // java/io/PrintStream
 *   #37 = NameAndType        #49:#50        // println:(Ljava/lang/String;)V
 *   #38 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/NewClassMain
 *   #39 = Utf8               java/lang/Object
 *   #40 = Utf8               getUser
 *   #41 = Utf8               ()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *   #42 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
 *   #43 = Utf8               getName
 *   #44 = Utf8               ()Ljava/lang/String;
 *   #45 = Utf8               java/lang/System
 *   #46 = Utf8               out
 *   #47 = Utf8               Ljava/io/PrintStream;
 *   #48 = Utf8               java/io/PrintStream
 *   #49 = Utf8               println
 *   #50 = Utf8               (Ljava/lang/String;)V
 * {
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.NewClassMain();
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
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/NewClassMain;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=4, args_size=1
 *          0: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService
 *          3: dup
 *          4: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService."<init>":()V
 *          7: astore_1
 *          8: aload_1
 *          9: invokevirtual #4                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService.getUser:()Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *         12: astore_2
 *         13: aload_2
 *         14: invokevirtual #5                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User.getName:()Ljava/lang/String;
 *         17: astore_3
 *         18: getstatic     #6                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         21: aload_3
 *         22: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         25: return
 *       LineNumberTable:
 *         line 12: 0
 *         line 13: 8
 *         line 14: 13
 *         line 15: 18
 *         line 16: 25
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      26     0  args   [Ljava/lang/String;
 *             8      18     1 service   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/UserService;
 *            13      13     2  user   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
 *            18       8     3  name   Ljava/lang/String;
 * }
 * SourceFile: "NewClassMain.java"
 */