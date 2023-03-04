package com.xjjlearning.jvm.deeptoeasy.classoperation;

/**
    静态方法, 操作数栈中没有this
 */
public class StaticMethod {
    static void show(String msg) {
        System.out.println(msg);
    }
    /*
        Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        // load "hello world!" from top of stack
         3: aload_0
         4: invokevirtual #3                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         7: return
     */

    public static void main(String[] args) {
        StaticMethod.show("hello word!");
    }
    /*
        Code:
      stack=1, locals=1, args_size=1
         0: ldc           #4                  // String hello word!
         2: invokestatic  #5                  // Method show:(Ljava/lang/String;)V
         5: return
     */
}
/*
javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.StaticMethodMain
Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain.class
  Last modified 2023-3-4; size 752 bytes
  MD5 checksum ab16dca8b4c64c4465ceafa1a8c29cfe
  Compiled from "StaticMethodMain.java"
public class com.xjjlearning.jvm.deeptoeasy.classoperation.StaticMethodMain
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#25         // java/lang/Object."<init>":()V
   #2 = Fieldref           #26.#27        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = Methodref          #28.#29        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #4 = String             #30            // hello word!
   #5 = Methodref          #6.#31         // com/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain.show:(Ljava/lang/String;)V
   #6 = Class              #32            // com/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain
   #7 = Class              #33            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
  #14 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain;
  #15 = Utf8               show
  #16 = Utf8               (Ljava/lang/String;)V
  #17 = Utf8               msg
  #18 = Utf8               Ljava/lang/String;
  #19 = Utf8               main
  #20 = Utf8               ([Ljava/lang/String;)V
  #21 = Utf8               args
  #22 = Utf8               [Ljava/lang/String;
  #23 = Utf8               SourceFile
  #24 = Utf8               StaticMethodMain.java
  #25 = NameAndType        #8:#9          // "<init>":()V
  #26 = Class              #34            // java/lang/System
  #27 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
  #28 = Class              #37            // java/io/PrintStream
  #29 = NameAndType        #38:#16        // println:(Ljava/lang/String;)V
  #30 = Utf8               hello word!
  #31 = NameAndType        #15:#16        // show:(Ljava/lang/String;)V
  #32 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain
  #33 = Utf8               java/lang/Object
  #34 = Utf8               java/lang/System
  #35 = Utf8               out
  #36 = Utf8               Ljava/io/PrintStream;
  #37 = Utf8               java/io/PrintStream
  #38 = Utf8               println
{
  public com.xjjlearning.jvm.deeptoeasy.classoperation.StaticMethodMain();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/StaticMethodMain;

  static void show(java.lang.String);
    descriptor: (Ljava/lang/String;)V
    flags: ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: aload_0
         4: invokevirtual #3                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         7: return
      LineNumberTable:
        line 5: 0
        line 6: 7
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       8     0   msg   Ljava/lang/String;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=1, args_size=1
         0: ldc           #4                  // String hello word!
         2: invokestatic  #5                  // Method show:(Ljava/lang/String;)V
         5: return
      LineNumberTable:
        line 9: 0
        line 10: 5
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       6     0  args   [Ljava/lang/String;
}
SourceFile: "StaticMethodMain.java"

 */