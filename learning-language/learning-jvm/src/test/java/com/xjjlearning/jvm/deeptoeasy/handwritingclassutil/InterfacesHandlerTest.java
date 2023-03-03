package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Class_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Utf8_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CpInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class InterfacesHandlerTest {

    @Test
    public void testInterfacesHandlerHandler() throws Exception {
        String filePath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/InterfacesHandler.class";
        ByteBuffer bf = ClassFileAnalysisMain.readFile(filePath);
        ClassFile cf = ClassFileAnalysiser.analyse(bf);

        System.out.println("Count of interfaces: " + cf.getInterfaces_count().toInt());

        // 获取常量池
        CpInfo[] constantPool = cf.getConstant_pool();

        // 全部接口
        Arrays.stream(cf.getInterfaces()).forEach(i -> getNameByIdx(constantPool, i.toInt()));
    }

    // 使用常量池中的索引拿到类的名字
    private void getNameByIdx(CpInfo[] constantPool, Integer idx) {
        // 由于常量池的索引是从1开始的，所以需要将索引减1取得数组下标
        CONSTANT_Class_info thisClassInfo = (CONSTANT_Class_info) constantPool[idx - 1];
        CONSTANT_Utf8_info thisClassName = (CONSTANT_Utf8_info) constantPool[thisClassInfo.getName_index().toInt() - 1];
        System.out.println(thisClassName);
    }
}