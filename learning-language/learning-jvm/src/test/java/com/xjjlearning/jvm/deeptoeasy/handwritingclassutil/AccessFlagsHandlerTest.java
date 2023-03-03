package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler.AccessFlagsHandler;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassAccessFlagUtils;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

public class AccessFlagsHandlerTest {

    @Test
    public void testAccessFlagsHandlerHandler() throws Exception {
        String path = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/Main.class";
        ByteBuffer bf = ClassFileAnalysisMain.readFile(path);
        ClassFile cf = ClassFileAnalysiser.analyse(bf);

        U2 flag = cf.getAccess_flags();
        if (flag != null)
            System.out.println(ClassAccessFlagUtils.toClassAccessFlagsString(flag));
        else
            System.out.println("Flag is null");
    }

}