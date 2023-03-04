package com.xjjlearning.jvm.deeptoeasy.classoperation;

import com.xjjlearning.jvm.deeptoeasy.classoperation.simple.BaseService;

/**
 * 调用super 但是在字节码层面没有super, 只load this
 * 关键点在invokespecial的时候指向父类的方法
 */
public class OverridedService extends BaseService {
    @Override
    public void testInvokeSuperMethod() {
        // 在重写的方法中调用父类的方法需要使用super关键字
        super.testInvokeSuperMethod();
    }

    /*
        Code:
      stack=1, locals=1, args_size=1
        // load this
         0: aload_0
        // 指向父类的方法
         1: invokespecial #2                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService.testInvokeSuperMethod:()V
         4: return
     */

    public static void main(String[] args) {
        // builder构造者模式的链式调用过程，解答本书第一章中提出的疑问，即使用build构造者模式时，链接调用方法会不会每次调用都将返回的对象引用先存储到局部变量表。
        // 因此，使用Builder构造者模式还有一个好处，就是减少字节码指令，提升程序的执行效率。
        new UserService().getUser().getName();
    }
}
/*
javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.OverridedService
Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService.class
  Last modified 2023-3-4; size 512 bytes
  MD5 checksum 266f25d7c6c65c0148a7ed18c0c769ad
  Compiled from "OverridedService.java"
public class com.xjjlearning.jvm.deeptoeasy.classoperation.OverridedService extends com.xjjlearning.jvm.deeptoeasy.classoperation.simple.BaseService
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#15         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService."<init>":()V
   #2 = Methodref          #4.#16         // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService.testInvokeSuperMethod:()V
   #3 = Class              #17            // com/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService
   #4 = Class              #18            // com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               LocalVariableTable
  #10 = Utf8               this
  #11 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService;
  #12 = Utf8               testInvokeSuperMethod
  #13 = Utf8               SourceFile
  #14 = Utf8               OverridedService.java
  #15 = NameAndType        #5:#6          // "<init>":()V
  #16 = NameAndType        #12:#6         // testInvokeSuperMethod:()V
  #17 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService
  #18 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService
{
  public com.xjjlearning.jvm.deeptoeasy.classoperation.OverridedService();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService;

  public void testInvokeSuperMethod();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #2                  // Method com/xjjlearning/jvm/deeptoeasy/classoperation/simple/BaseService.testInvokeSuperMethod:()V
         4: return
      LineNumberTable:
        line 9: 0
        line 10: 4
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/OverridedService;
}
SourceFile: "OverridedService.java"
 */