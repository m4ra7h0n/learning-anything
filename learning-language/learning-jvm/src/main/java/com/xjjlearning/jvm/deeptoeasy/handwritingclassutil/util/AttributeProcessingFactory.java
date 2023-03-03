package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.AttributeInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U4;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute.Code_attribute;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute.ConstantValue_attribute;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

public class AttributeProcessingFactory {
    // AttributeInfo转化为ConstantValue_attribute

    /**
     * @param attributeInfo 经过一次解析后的ConstantValue属性对象
     * @return 方法返回二次解析后生成的ConstantValue属性对象
     */
    public static ConstantValue_attribute processingConstantValue(AttributeInfo attributeInfo) {
        ConstantValue_attribute attribute = new ConstantValue_attribute();
        attribute.setAttribute_name_index(attributeInfo.getAttribute_name_index());
        attribute.setAttribute_length(attributeInfo.getAttribute_length());
        attribute.setConstantvalue_index(new U2(attributeInfo.getInfo()[0], attributeInfo.getInfo()[1]));
        return attribute;
    }

    /**
     * @param attributeInfo 经过一次解析后的ConstantValue属性对象
     * @return 返回二次解析后生成的Code属性对象
     */
    public static Code_attribute processingCode(AttributeInfo attributeInfo) {
        Code_attribute code = new Code_attribute();
        ByteBuffer body = ByteBuffer.wrap(attributeInfo.getInfo());
        // 操作数栈大小
        code.setMax_stack(new U2(body.get(), body.get()));
        // 局部变量表大小
        code.setMax_locals(new U2(body.get(), body.get()));
        // 字节码数组长度
        code.setCode_length(new U4(body.get(), body.get(), body.get(), body.get()));
        // 解析获取字节码
        byte[] byteCode = new byte[code.getCode_length().toInt()];
        body.get(byteCode, 0, byteCode.length);
        code.setCode(byteCode);
        return code;
    }
}