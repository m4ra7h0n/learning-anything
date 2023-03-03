package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U4;
import lombok.Getter;
import lombok.Setter;

/**
 * 常量属性 static
 */
@Getter
@Setter
public class ConstantValue_attribute {
    // 只有同时被final和static修饰的字段才有ConstantValue属性，且限于基本类型和String
    // 为什么只能基本类型和String? -> 从常量池中只能引用到基本类型和String类型的字面量
    // static final修饰的字段在javac编译时生成constantValue属性，在类加载的准备阶段直接把constantValue的值赋给该字段。

    /**
     * 由于ConstantValue属性是定长属性，因此attribute_length的值固定为2；
     */
    private U2 attribute_name_index;
    /**
     * 指向常量池中的CONSTANT_Utf8_info常量，该常量表示的字符串为“ConstantValue”；
     */
    private U4 attribute_length;
    /**
     * 指向基本数据类型或String类型常量。
     */
    private U2 constantvalue_index;
}
