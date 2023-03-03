package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

public class AllHandlerTest {

    @Test
    public void test() throws Exception {  
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile("/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/test/Test.class");
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);

        int remaining = codeBuf.remaining();
        System.out.println("剩余字节数" + remaining);
    }

}