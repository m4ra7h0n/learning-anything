package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.AttributeInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.MethodInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.attribute.Code_attribute;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Utf8_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.AttributeProcessingFactory;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.HexStringUtils;
import org.junit.Test;

import java.nio.ByteBuffer;

public class CodeAttributeTest {

    @Test
    public void testCodeAttribute() throws Exception {
        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/RecursionAlgorithmMain.class";
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(filePath);
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        // 获取方法表  
        MethodInfo[] methodInfos = classFile.getMethods();
        // 遍历方法表
        for (MethodInfo methodInfo : methodInfos) {
            // 获取方法的属性表
            AttributeInfo[] attributeInfos = methodInfo.getAttributes();
            if (attributeInfos == null || attributeInfos.length == 0) {
                continue;
            }
            System.out.println("方法：" + classFile.getConstant_pool()[methodInfo.getName_index().toInt() - 1]);
            // 遍历属性表
            for (AttributeInfo attributeInfo : attributeInfos) {
                // 获取属性的名称
                U2 name_index = attributeInfo.getAttribute_name_index();
                CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info) classFile.getConstant_pool()[name_index.toInt() - 1];
                String name = new String(name_info.getBytes());
                // 对Code属性二次解析
                if (name.equalsIgnoreCase("Code")) {
                    // 属性二次解析
                    Code_attribute code = AttributeProcessingFactory.processingCode(attributeInfo);
                    System.out.println("操作数栈大小：" + code.getMax_stack().toInt());
                    System.out.println("局部变量表大小：" + code.getMax_locals().toInt());
                    System.out.println("字节码数组长度：" + code.getCode_length().toInt());
                    System.out.println("字节码：");
                    for (byte b : code.getCode()) {
                        System.out.print((b & 0xff) + " ");
                    }
//                    System.out.println(HexStringUtils.toHexString(code.getCode()));
                    System.out.println("\n");
                }
            }
        }
    }

}