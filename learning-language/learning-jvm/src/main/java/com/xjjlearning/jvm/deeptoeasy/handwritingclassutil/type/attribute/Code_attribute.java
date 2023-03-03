package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.AttributeInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U4;
import lombok.Getter;
import lombok.Setter;

/**
 * 一个方法编译后生成的字节码指令存储在方法结构的属性表的Code属性中，
 * 但并非每个方法都有Code属性，如声明为native的方法、abstract抽象方法、接口中的非default方法就没有Code属性。
 * 实例初始化方法、类或接口的初始化方法都会有Code属性。
 */
@Setter
@Getter
public class Code_attribute {

    private U2 attribute_name_index; // 指向常量池中“Code”常量的索引
    private U4 attribute_length;
    private U2 max_stack; // 操作数栈的最大深度
    private U2 max_locals; // 局部变量表的最大深度
    private U4 code_length;
    private byte[] code; // 字节码指令
    private U4 exception_table_length;
    private Exception[] exception_table; // 异常表
    private U2 attributes_count;
    /**
     * 属性也可以有属性表，attributes项便是Code属性的属性表，在Code属性中，属性表可能存在的属性如LineNumerTable属性、LocalVariableTable属性。
     * LineNumerTable属性: 被用来映射源码文件中给定的代码行号对应code[]字节码指令中的哪一部分，在调试时用到，在方法抛出异常打印异常栈信息也会用到。
     * LocalVariableTable属性: 用来描述code[]中的某个范围内，局部变量表中某个位置存储的变量的名称是什么，用于与源码文件中局部变量名做映射。该属性不一定会编译到class文件中，如果没有该属性，那么查看反编译后的java代码将会使用诸如arg0、arg1、arg2之类的名称代替局部变量的名称。
     */
    private AttributeInfo[] attributes;

    /**
     * exception_table用于存储方法中的所有try-catch信息, 异常表的每一项都是固定的结构体.
     * 异常表中每项的结构
     */
    public static class Exception {
        private U2 start_pc; // try的开始位置在code[]中的索引
        private U2 end_pc; // try的结束位置在code[]中的索引。
        private U2 handler_pc; // 异常处理的起点在code[]中的索引。
        /**
         * 为0相当finally块。
         * 不为0时，指向常量池中某个CONSTANT_Class_info常量的索引，表示需要捕获的异常
         * 只有[start_pc,end_pc)范围内抛出的异常是指定的类或子类的实例，才会跳转到handler_pc指向的字节码指令继续执行。
         */
        private U2 catch_type;
    }
}