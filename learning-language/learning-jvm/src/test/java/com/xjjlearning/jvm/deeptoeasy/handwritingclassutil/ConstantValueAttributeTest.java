package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.*;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute.ConstantValue_attribute;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.*;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.AttributeProcessingFactory;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 *
 */
public class ConstantValueAttributeTest {
    @Test
    public void testConstantValue() throws Exception {
        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/TestConstantValueInterface.class";

        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(filePath);
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        // 获取所有字段
        FieldInfo[] fieldInfos = classFile.getFields();
        for (FieldInfo fieldInfo : fieldInfos) {
            // 获取字段的所有属性
            AttributeInfo[] attributeInfos = fieldInfo.getAttributes();
            if (attributeInfos == null || attributeInfos.length == 0) {
                continue;
            }
            System.out.println("字段：" + classFile.getConstant_pool()[fieldInfo.getName_index().toInt() - 1]);
            // 遍历所有属性
            for (AttributeInfo attributeInfo : attributeInfos) {
                // 获取属性的名称
                U2 name_index = attributeInfo.getAttribute_name_index();
                CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info) classFile.getConstant_pool()[name_index.toInt() - 1];
                String name = new String(name_info.getBytes());
                // 如果属性名是ConstantValue，则对该属性进行二次解析
                // AttributeInfo.name_index 指向的 CONSTANT_Utf8_info 是 ConstantValue 则AttributeInfo.bytes的内容是其值的索引
                if (name.equalsIgnoreCase("ConstantValue")) {
                    // 属性二次解析
                    ConstantValue_attribute constantValue = AttributeProcessingFactory.processingConstantValue(attributeInfo);
                    // 取得constantvalue_index，从常量池中取值
                    U2 cv_index = constantValue.getConstantvalue_index();
                    Object cv = classFile.getConstant_pool() [cv_index.toInt() - 1];
                    // 需要判断常量的类型
                    if (cv instanceof CONSTANT_Utf8_info) { // String
                        System.out.println("ConstantValue：" + cv.toString());
                    } else if (cv instanceof CONSTANT_Integer_info) { // int, short, char, byte, boolean
                        System.out.println("ConstantValue：" + ((CONSTANT_Integer_info) cv).getBytes().toInt());
                    } else if (cv instanceof CONSTANT_Float_info) { // float
                        // todo
                    } else if (cv instanceof CONSTANT_Long_info) { // long
                        // todo
                    } else if (cv instanceof CONSTANT_Double_info) { // double
                        // todo
                    }
                }
            }
        }
    }
}