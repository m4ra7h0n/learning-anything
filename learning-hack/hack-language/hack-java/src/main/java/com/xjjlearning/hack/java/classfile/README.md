# 学习字节码指令文章
https://tobebetterjavaer.com/jvm/zijiema-zhiling.html

# 如何使用命令生成字节码信息
```bash
# 生成class文件
javac TestClass.java
# 生成class文件详细信息
# javap用于分析class文件字节码
javap -verbose TestClass
```

```text
Classfile /Users/xjj/IdeaProjects/learning-anything/learning-hack/hack-language/hack-java/src/main/java/com/xjjlearning/hack/java/classfile/TestClass.class
  Last modified 2023-1-17; size 311 bytes
  MD5 checksum a9c332d0ea24eec21286f3dc69a79a2d
  Compiled from "TestClass.java"
public class com.xjjlearning.hack.java.classfile.TestClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#15         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#16         // com/xjjlearning/hack/java/classfile/TestClass.m:I
   #3 = Class              #17            // com/xjjlearning/hack/java/classfile/TestClass
   #4 = Class              #18            // java/lang/Object
   #5 = Utf8               m
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               inc
  #12 = Utf8               ()I
  #13 = Utf8               SourceFile
  #14 = Utf8               TestClass.java
  #15 = NameAndType        #7:#8          // "<init>":()V
  #16 = NameAndType        #5:#6          // m:I
  #17 = Utf8               com/xjjlearning/hack/java/classfile/TestClass
  #18 = Utf8               java/lang/Object
{
  public com.xjjlearning.hack.java.classfile.TestClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0

  public int inc();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field m:I
         4: iconst_1
         5: iadd
         6: ireturn
      LineNumberTable:
        line 10: 0
}
SourceFile: "TestClass.java"

```