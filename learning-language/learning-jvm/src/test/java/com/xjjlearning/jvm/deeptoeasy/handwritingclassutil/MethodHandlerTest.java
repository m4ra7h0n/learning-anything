package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.MethodInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Utf8_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.FieldAccessFlagUtils;
import org.junit.Test;

import java.nio.ByteBuffer;

public class MethodHandlerTest {

    private static String getName(U2 name_index, ClassFile classFile) {
        CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()[name_index.toInt() - 1];
        return name_info.toString();
    }

    @Test
    public void testMethodHandlerHandler() throws Exception {
        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/Test.class";
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(filePath);
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        System.out.println("方法总数:" + classFile.getMethods_count().toInt());
        System.out.println();
        MethodInfo[] methodInfos = classFile.getMethods();
        // 遍历方法表
        // 作者写的看着好难受..
        for (MethodInfo methodInfo : methodInfos) {
            System.out.println("访问标志和属性：" + FieldAccessFlagUtils
                    .toFieldAccessFlagsString(methodInfo.getAccess_flags()));
            System.out.println("方法名：" + getName(methodInfo.getName_index(), classFile));
            System.out.println("方法描述符："
                    + getName(methodInfo.getDescriptor_index(), classFile));
            System.out.println("属性总数：" + methodInfo.getAttributes_count().toInt());
            System.out.println();
        }
    }
}