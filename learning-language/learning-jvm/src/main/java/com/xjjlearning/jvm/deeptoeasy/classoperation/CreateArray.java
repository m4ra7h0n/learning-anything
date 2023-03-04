package com.xjjlearning.jvm.deeptoeasy.classoperation;

import com.xjjlearning.jvm.deeptoeasy.classoperation.simple.User;

/**
 * Created by xjj on 2023/3/4.
 */

/**
 * 学习数组创建的字节码
 */
public class CreateArray {
    /**
     * 基本数据类型数组, 不加a
     */
    public static void baseArray() {
        int[] array = new int[2];
        array[1] = 100;
        int a = array[1];
    }
    /*
     Code:
      stack=3, locals=2, args_size=0
        // 将2压入栈顶 即设置数组的大小为2
         0: iconst_2
        // 创建一个指定原始类型（如int, float, char…）的数组，并将其引用值压入操作栈栈顶
         1: newarray       int
         3: astore_0

         4: aload_0
         5: iconst_1
         6: bipush        100
        // 将操作栈栈顶int型数值存入指定数组的指定索引位置
         8: iastore

         9: aload_0
        10: iconst_1
        11: iaload
        12: istore_1
        13: return
     */

    /*
      引用类型数组, 加a就代表引用
     */
    public static void objArray(){
        User[] users = new User[2];
        users[0] = new User();
        users[1] = users[0];
    }
    /*
     public static void objArray();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=1, args_size=0
         0: iconst_2
        // 创建引用类型的数组
         1: anewarray     #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
         4: astore_0

         5: aload_0
         6: iconst_0
         7: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
        10: dup
        11: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
       // 将栈顶引用类型值保存到引用类型数组的指定索引位置处
        14: aastore

        15: aload_0
        16: iconst_1
        17: aload_0
        18: iconst_0
       // 将引用类型数组中指定索引的元素放入操作数栈顶
        19: aaload
        20: aastore
        21: return

      LineNumberTable:
        line 41: 0
        line 42: 5
        line 43: 15
        line 44: 21
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            5      17     0 users   [Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
     */
    public static void main(String[] args) {
    }
}
/*
javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.CreateArray
Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/CreateArray.class
  Last modified 2023-3-4; size 833 bytes
  MD5 checksum b9194eb952522e25a0320268079e9df2
  Compiled from "CreateArray.java"
public class com.xjjlearning.jvm.deeptoeasy.classoperation.CreateArray
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#27         // java/lang/Object."<init>":()V
   #2 = Class              #28            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
   #3 = Methodref          #2.#27         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
   #4 = Class              #29            // com/xjjlearning/jvm/deeptoeasy/classoperation/CreateArray
   #5 = Class              #30            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               LocalVariableTable
  #11 = Utf8               this
  #12 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/CreateArray;
  #13 = Utf8               baseArray
  #14 = Utf8               array
  #15 = Utf8               [I
  #16 = Utf8               a
  #17 = Utf8               I
  #18 = Utf8               objArray
  #19 = Utf8               users
  #20 = Utf8               [Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;
  #21 = Utf8               main
  #22 = Utf8               ([Ljava/lang/String;)V
  #23 = Utf8               args
  #24 = Utf8               [Ljava/lang/String;
  #25 = Utf8               SourceFile
  #26 = Utf8               CreateArray.java
  #27 = NameAndType        #6:#7          // "<init>":()V
  #28 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
  #29 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/CreateArray
  #30 = Utf8               java/lang/Object
{
  public com.xjjlearning.jvm.deeptoeasy.classoperation.CreateArray();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 9: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/CreateArray;

  public static void baseArray();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=2, args_size=0
         0: iconst_2
         1: newarray       int
         3: astore_0
         4: aload_0
         5: iconst_1
         6: bipush        100
         8: iastore
         9: aload_0
        10: iconst_1
        11: iaload
        12: istore_1
        13: return
      LineNumberTable:
        line 14: 0
        line 15: 4
        line 16: 9
        line 17: 13
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            4      10     0 array   [I
           13       1     1     a   I

  public static void objArray();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=1, args_size=0
         0: iconst_2
         1: anewarray     #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
         4: astore_0
         5: aload_0
         6: iconst_0
         7: new           #2                  // class com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User
        10: dup
        11: invokespecial #3                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/User."<init>":()V
        14: aastore
        15: aload_0
        16: iconst_1
        17: aload_0
        18: iconst_0
        19: aaload
        20: aastore
        21: return
      LineNumberTable:
        line 41: 0
        line 42: 5
        line 43: 15
        line 44: 21
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            5      17     0 users   [Lcom/xjjlearning/jvm/deeptoeasy/classoperation/simple/User;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 46: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  args   [Ljava/lang/String;
}
SourceFile: "CreateArray.java"

 */
