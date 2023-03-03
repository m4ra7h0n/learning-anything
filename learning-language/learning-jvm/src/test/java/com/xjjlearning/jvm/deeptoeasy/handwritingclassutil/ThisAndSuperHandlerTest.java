package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Class_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Utf8_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CpInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

public class ThisAndSuperHandlerTest {
    @Test
    public void testThisAndSuperHandlerHandler() throws Exception {
        ByteBuffer bf = ClassFileAnalysisMain.readFile("/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/Test.class");
        ClassFile cf = ClassFileAnalysiser.analyse(bf);

        // 获取常量池
        CpInfo[] constantPool = cf.getConstant_pool();

        // 获取this_class以及其指向的索引
//        U2 thisClass = cf.getThis_class();
//        Integer thisClassIdx = thisClass.toInt();
//        f(constantPool, thisClassIdx);

        // super 索引
        U2 superClass = cf.getSuper_class();
        Integer superClassIdx = superClass.toInt();
        f(constantPool, superClassIdx);
    }

    // 使用常量池中的索引拿到类的名字
    private void f(CpInfo[] constantPool, Integer tag) {
        // 由于常量池的索引是从1开始的，所以需要将索引减1取得数组下标
        CONSTANT_Class_info thisClassInfo = (CONSTANT_Class_info) constantPool[tag - 1];
        CONSTANT_Utf8_info thisClassName = (CONSTANT_Utf8_info) constantPool[thisClassInfo.getName_index().toInt() - 1];
        System.out.println(thisClassName);
    }
}