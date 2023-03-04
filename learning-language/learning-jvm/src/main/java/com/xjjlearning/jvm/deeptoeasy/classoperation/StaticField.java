package com.xjjlearning.jvm.deeptoeasy.classoperation;

/*
    静态字段, 操作数栈中没有this
 */
public class StaticField {
    static String name;

    public static void main(String[] args) {
        name = "xjj";
        System.out.println(name);
    }

    /*
        Code:
      stack=2, locals=1, args_size=1
         0: ldc           #2                  // String xjj
        // putstatic指令将栈顶的元素赋值给类的静态字段 #3
         2: putstatic     #3                  // Field name:Ljava/lang/String;

         5: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
         8: getstatic     #3                  // Field name:Ljava/lang/String;
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        14: return
     */
}
/*
javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.StaticFieldMain
Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain.class
  Last modified 2023-3-4; size 688 bytes
  MD5 checksum 1949cb550601db5e61d847d10e41bbdb
  Compiled from "StaticFieldMain.java"
public class com.xjjlearning.jvm.deeptoeasy.classoperation.StaticFieldMain
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#23         // java/lang/Object."<init>":()V
   #2 = String             #24            // xjj
   #3 = Fieldref           #6.#25         // com/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain.name:Ljava/lang/String;
   #4 = Fieldref           #26.#27        // java/lang/System.out:Ljava/io/PrintStream;
   #5 = Methodref          #28.#29        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #6 = Class              #30            // com/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain
   #7 = Class              #31            // java/lang/Object
   #8 = Utf8               name
   #9 = Utf8               Ljava/lang/String;
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain;
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               args
  #20 = Utf8               [Ljava/lang/String;
  #21 = Utf8               SourceFile
  #22 = Utf8               StaticFieldMain.java
  #23 = NameAndType        #10:#11        // "<init>":()V
  #24 = Utf8               xjj
  #25 = NameAndType        #8:#9          // name:Ljava/lang/String;
  #26 = Class              #32            // java/lang/System
  #27 = NameAndType        #33:#34        // out:Ljava/io/PrintStream;
  #28 = Class              #35            // java/io/PrintStream
  #29 = NameAndType        #36:#37        // println:(Ljava/lang/String;)V
  #30 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain
  #31 = Utf8               java/lang/Object
  #32 = Utf8               java/lang/System
  #33 = Utf8               out
  #34 = Utf8               Ljava/io/PrintStream;
  #35 = Utf8               java/io/PrintStream
  #36 = Utf8               println
  #37 = Utf8               (Ljava/lang/String;)V
{
  static java.lang.String name;
    descriptor: Ljava/lang/String;
    flags: ACC_STATIC

  public com.xjjlearning.jvm.deeptoeasy.classoperation.StaticFieldMain();
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
            0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/StaticFieldMain;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: ldc           #2                  // String xjj
         2: putstatic     #3                  // Field name:Ljava/lang/String;
         5: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
         8: getstatic     #3                  // Field name:Ljava/lang/String;
        11: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        14: return
      LineNumberTable:
        line 7: 0
        line 8: 5
        line 9: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  args   [Ljava/lang/String;
}
SourceFile: "StaticFieldMain.java"

 */