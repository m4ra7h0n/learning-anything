package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.FieldInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Class_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Utf8_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CpInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.FieldAccessFlagUtils;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FieldHandlerTest {
    @Test
    public void testFieldHandlerHandler() throws Exception {
        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/InterfacesHandler.class";
//        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/Test.class";
        ByteBuffer bf = ClassFileAnalysisMain.readFile(filePath);
        ClassFile cf = ClassFileAnalysiser.analyse(bf);
        U2 fieldsCount = cf.getFields_count();
        System.out.println("字段总数: " + fieldsCount.toInt());

        if (fieldsCount.toInt() == 0) {
            return;
        }

        FieldInfo[] fieldsInfo = cf.getFields();
        Arrays.stream(fieldsInfo)
                .map(fieldInfo -> {
                    return "访问标识和属性: " + FieldAccessFlagUtils.toFieldAccessFlagsString(fieldInfo.getAccess_flags()) +
                            "\n字段名" + getName(fieldInfo.getName_index(), cf) +
                            "\n字段的类型描述符: " + getName(fieldInfo.getDescriptor_index(), cf) +
                            "\n属性总数: " + fieldInfo.getAttributes_count().toInt() +
                            "\n";
                })
                .forEach(System.out::println);
    }

    // 使用常量池中的索引拿到类的名字
    private static String getName(U2 name_index, ClassFile classFile) {
        CONSTANT_Utf8_info name_info = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()[name_index.toInt() - 1];
        return name_info.toString();
    }
}