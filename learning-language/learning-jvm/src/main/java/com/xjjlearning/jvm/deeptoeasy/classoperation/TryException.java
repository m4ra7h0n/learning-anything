package com.xjjlearning.jvm.deeptoeasy.classoperation;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by xjj on 2023/3/4.
 */
public class TryException {
    public int tryCatchDemo() {
        try {
            int n = 100;
            int m = 0;
            return n / m;
        } catch (ArithmeticException e) {
            return -1;
        }
    }
    /*
     *   public int tryCatchDemo();
     *     descriptor: ()I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=3, args_size=1
     *          0: bipush        100
     *          2: istore_1
     *          3: iconst_0
     *          4: istore_2
     *          5: iload_1
     *          6: iload_2
     *          7: idiv
     *          8: ireturn
     *          9: astore_1
     *         10: iconst_m1
     *         11: ireturn
     *       Exception table:
     *  // 从0到7是一个异常块 如果发生异常跳转到9
     *          from    to  target type
     *              0     8     9   Class java/lang/ArithmeticException
     *       LineNumberTable:
     *         line 12: 0
     *         line 13: 3
     *         line 14: 5
     *         line 15: 9
     *         line 16: 10
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             3       6     1     n   I
     *             5       4     2     m   I
     *            10       2     1     e   Ljava/lang/ArithmeticException;
     *             0      12     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
     */

    public int tryCatchFinalDemo() {
        try {
            int n = 100;
            int m = 0;
            return n / m;
        } catch (ArithmeticException e) {
            return -1;
        } finally {
            System.out.println("finally");
        }
    }
    /*
     *   public int tryCatchFinalDemo();
     *     descriptor: ()I
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=2, locals=5, args_size=1
     *      // try
     *          0: bipush        100
     *          2: istore_1
     *          3: iconst_0
     *          4: istore_2
     *          5: iload_1
     *          6: iload_2
     *          7: idiv
     *          8: istore_3
     *
     *      // finally
     *          9: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *         12: ldc           #4                  // String finally
     *         14: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         17: iload_3
     *         18: ireturn
     *
     *      // catch
     *         19: astore_1
     *         20: iconst_m1
     *         21: istore_2
     *
     *      // finally
     *         22: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *         25: ldc           #4                  // String finally
     *         27: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         30: iload_2
     *         31: ireturn
     *         32: astore        4
     *
     *      // finally
     *         34: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *         37: ldc           #4                  // String finally
     *         39: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         42: aload         4
     *         44: athrow
     *       Exception table:
     *          from    to  target type
     *              0     9    19   Class java/lang/ArithmeticException
     *              0     9    32   any
     *             19    22    32   any
     *             32    34    32   any
     */

    public void tryWithResource() {
        try (InputStream in = new FileInputStream("/tmp/xxx.xlsx")) {
            // 读取文件
        } catch (Exception ignore) {
        }
    }
    /*
     *   public void tryWithResource();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=3, locals=4, args_size=1
     *          0: new           #6                  // class java/io/FileInputStream
     *          3: dup
     *          4: ldc           #7                  // String /tmp/xxx.xlsx
     *          6: invokespecial #8                  // Method java/io/FileInputStream."<init>":(Ljava/lang/String;)V
     *          9: astore_1
     *         10: aconst_null   -> ?
     *         11: astore_2         \
     *         12: aload_1           \ 这段可以理解为以下不: 2放了null, 如果in是null直接结束, 如果in不是null则跳转到36调用close, 没有20-33的事情
     *         13: ifnull        40   \
     *         16: aload_2             \
     *         17: ifnull        36  -> ?
     *         20: aload_1
     *      // 如果局部变量in不为null，且try块抛出的异常不为null，调用close方法
     *         21: invokevirtual #9                  // Method java/io/InputStream.close:()V
     *         24: goto          40
     *         27: astore_3
     *         28: aload_2
     *         29: aload_3
     *      // 调用addSuppressed方法将close方法抛出的异常添加到try代码块抛出的异常
     *         30: invokevirtual #11                 // Method java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
     *         33: goto          40
     *         36: aload_1
     *         37: invokevirtual #9                  // Method java/io/InputStream.close:()V
     *         40: goto          44
     *         43: astore_1
     *         44: return
     *       Exception table:
     *          from    to  target type
     *          // 自动生成的close代码也添加try/catch块
     *             20    24    27   Class java/lang/Throwable
     *              0    40    43   Class java/lang/Exception
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *            10      30     1    in   Ljava/io/InputStream;
     *             0      45     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
     */

    public static void main(String[] args) {

    }
}
/**
 * javap -v com.xjjlearning.jvm.deeptoeasy.classoperation.TryException
 * Classfile /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/classoperation/TryException.class
 *   Last modified 2023-3-4; size 1555 bytes
 *   MD5 checksum ee95128eb6e6ec943d3c7cc9d8d8cabe
 *   Compiled from "TryException.java"
 * public class com.xjjlearning.jvm.deeptoeasy.classoperation.TryException
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #14.#45        // java/lang/Object."<init>":()V
 *    #2 = Class              #46            // java/lang/ArithmeticException
 *    #3 = Fieldref           #47.#48        // java/lang/System.out:Ljava/io/PrintStream;
 *    #4 = String             #49            // finally
 *    #5 = Methodref          #50.#51        // java/io/PrintStream.println:(Ljava/lang/String;)V
 *    #6 = Class              #52            // java/io/FileInputStream
 *    #7 = String             #53            // /tmp/xxx.xlsx
 *    #8 = Methodref          #6.#54         // java/io/FileInputStream."<init>":(Ljava/lang/String;)V
 *    #9 = Methodref          #55.#56        // java/io/InputStream.close:()V
 *   #10 = Class              #57            // java/lang/Throwable
 *   #11 = Methodref          #10.#58        // java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
 *   #12 = Class              #59            // java/lang/Exception
 *   #13 = Class              #60            // com/xjjlearning/jvm/deeptoeasy/classoperation/TryException
 *   #14 = Class              #61            // java/lang/Object
 *   #15 = Utf8               <init>
 *   #16 = Utf8               ()V
 *   #17 = Utf8               Code
 *   #18 = Utf8               LineNumberTable
 *   #19 = Utf8               LocalVariableTable
 *   #20 = Utf8               this
 *   #21 = Utf8               Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
 *   #22 = Utf8               tryCatchDemo
 *   #23 = Utf8               ()I
 *   #24 = Utf8               n
 *   #25 = Utf8               I
 *   #26 = Utf8               m
 *   #27 = Utf8               e
 *   #28 = Utf8               Ljava/lang/ArithmeticException;
 *   #29 = Utf8               StackMapTable
 *   #30 = Class              #46            // java/lang/ArithmeticException
 *   #31 = Utf8               tryCatchFinalDemo
 *   #32 = Class              #57            // java/lang/Throwable
 *   #33 = Utf8               tryWithResource
 *   #34 = Utf8               in
 *   #35 = Utf8               Ljava/io/InputStream;
 *   #36 = Class              #60            // com/xjjlearning/jvm/deeptoeasy/classoperation/TryException
 *   #37 = Class              #62            // java/io/InputStream
 *   #38 = Class              #59            // java/lang/Exception
 *   #39 = Utf8               main
 *   #40 = Utf8               ([Ljava/lang/String;)V
 *   #41 = Utf8               args
 *   #42 = Utf8               [Ljava/lang/String;
 *   #43 = Utf8               SourceFile
 *   #44 = Utf8               TryException.java
 *   #45 = NameAndType        #15:#16        // "<init>":()V
 *   #46 = Utf8               java/lang/ArithmeticException
 *   #47 = Class              #63            // java/lang/System
 *   #48 = NameAndType        #64:#65        // out:Ljava/io/PrintStream;
 *   #49 = Utf8               finally
 *   #50 = Class              #66            // java/io/PrintStream
 *   #51 = NameAndType        #67:#68        // println:(Ljava/lang/String;)V
 *   #52 = Utf8               java/io/FileInputStream
 *   #53 = Utf8               /tmp/xxx.xlsx
 *   #54 = NameAndType        #15:#68        // "<init>":(Ljava/lang/String;)V
 *   #55 = Class              #62            // java/io/InputStream
 *   #56 = NameAndType        #69:#16        // close:()V
 *   #57 = Utf8               java/lang/Throwable
 *   #58 = NameAndType        #70:#71        // addSuppressed:(Ljava/lang/Throwable;)V
 *   #59 = Utf8               java/lang/Exception
 *   #60 = Utf8               com/xjjlearning/jvm/deeptoeasy/classoperation/TryException
 *   #61 = Utf8               java/lang/Object
 *   #62 = Utf8               java/io/InputStream
 *   #63 = Utf8               java/lang/System
 *   #64 = Utf8               out
 *   #65 = Utf8               Ljava/io/PrintStream;
 *   #66 = Utf8               java/io/PrintStream
 *   #67 = Utf8               println
 *   #68 = Utf8               (Ljava/lang/String;)V
 *   #69 = Utf8               close
 *   #70 = Utf8               addSuppressed
 *   #71 = Utf8               (Ljava/lang/Throwable;)V
 * {
 *   public com.xjjlearning.jvm.deeptoeasy.classoperation.TryException();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 9: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
 *
 *   public int tryCatchDemo();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: bipush        100
 *          2: istore_1
 *          3: iconst_0
 *          4: istore_2
 *          5: iload_1
 *          6: iload_2
 *          7: idiv
 *          8: ireturn
 *          9: astore_1
 *         10: iconst_m1
 *         11: ireturn
 *       Exception table:
 *          from    to  target type
 *              0     8     9   Class java/lang/ArithmeticException
 *       LineNumberTable:
 *         line 12: 0
 *         line 13: 3
 *         line 14: 5
 *         line 15: 9
 *         line 16: 10
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             3       6     1     n   I
 *             5       4     2     m   I
 *            10       2     1     e   Ljava/lang/ArithmeticException;
 *             0      12     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
 *       StackMapTable: number_of_entries = 1
 *         frame_type = 73 /* same_locals_1_stack_item /
 *           stack = [ class java/lang/ArithmeticException ]
 *
 *   public int tryCatchFinalDemo();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=5, args_size=1
 *          0: bipush        100
 *          2: istore_1
 *          3: iconst_0
 *          4: istore_2
 *          5: iload_1
 *          6: iload_2
 *          7: idiv
 *          8: istore_3
 *          9: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         12: ldc           #4                  // String finally
 *         14: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         17: iload_3
 *         18: ireturn
 *         19: astore_1
 *         20: iconst_m1
 *         21: istore_2
 *         22: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         25: ldc           #4                  // String finally
 *         27: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         30: iload_2
 *         31: ireturn
 *         32: astore        4
 *         34: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         37: ldc           #4                  // String finally
 *         39: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *         42: aload         4
 *         44: athrow
 *       Exception table:
 *          from    to  target type
 *              0     9    19   Class java/lang/ArithmeticException
 *              0     9    32   any
 *             19    22    32   any
 *             32    34    32   any
 *       LineNumberTable:
 *         line 22: 0
 *         line 23: 3
 *         line 24: 5
 *         line 28: 9
 *         line 24: 17
 *         line 25: 19
 *         line 26: 20
 *         line 28: 22
 *         line 26: 30
 *         line 28: 32
 *         line 29: 42
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             3      16     1     n   I
 *             5      14     2     m   I
 *            20      12     1     e   Ljava/lang/ArithmeticException;
 *             0      45     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
 *       StackMapTable: number_of_entries = 2
 *         frame_type = 83 /* same_locals_1_stack_item /
 *           stack = [ class java/lang/ArithmeticException ]
 *         frame_type = 76 /* same_locals_1_stack_item /
 *           stack = [ class java/lang/Throwable ]
 *
 *   public void tryWithResource();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=4, args_size=1
 *          0: new           #6                  // class java/io/FileInputStream
 *          3: dup
 *          4: ldc           #7                  // String /tmp/xxx.xlsx
 *          6: invokespecial #8                  // Method java/io/FileInputStream."<init>":(Ljava/lang/String;)V
 *          9: astore_1
 *         10: aconst_null
 *         11: astore_2
 *         12: aload_1
 *         13: ifnull        40
 *         16: aload_2
 *         17: ifnull        36
 *         20: aload_1
 *         21: invokevirtual #9                  // Method java/io/InputStream.close:()V
 *         24: goto          40
 *         27: astore_3
 *         28: aload_2
 *         29: aload_3
 *         30: invokevirtual #11                 // Method java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
 *         33: goto          40
 *         36: aload_1
 *         37: invokevirtual #9                  // Method java/io/InputStream.close:()V
 *         40: goto          44
 *         43: astore_1
 *         44: return
 *       Exception table:
 *          from    to  target type
 *             20    24    27   Class java/lang/Throwable
 *              0    40    43   Class java/lang/Exception
 *       LineNumberTable:
 *         line 33: 0
 *         line 35: 12
 *         line 36: 40
 *         line 35: 43
 *         line 37: 44
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *            10      30     1    in   Ljava/io/InputStream;
 *             0      45     0  this   Lcom/xjjlearning/jvm/deeptoeasy/classoperation/TryException;
 *       StackMapTable: number_of_entries = 5
 *         frame_type = 255 /* full_frame /
 *           offset_delta = 27
 *           locals = [ class com/xjjlearning/jvm/deeptoeasy/classoperation/TryException, class java/io/InputStream, class java/lang/Throwable ]
 *           stack = [ class java/lang/Throwable ]
 *         frame_type = 8 /* same /
 *         frame_type = 249 /* chop /
 *           offset_delta = 3
 *         frame_type = 66 /* same_locals_1_stack_item /
 *           stack = [ class java/lang/Exception ]
 *         frame_type = 0 /* same /
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 41: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  args   [Ljava/lang/String;
 * }
 * SourceFile: "TryException.java"
 */
