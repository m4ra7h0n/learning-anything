package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.ClassFileAnalysisMain;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/11
 */
public class VersionHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 1;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 minorV = new U2(codeBuf.get(), codeBuf.get());
        U2 majorV = new U2(codeBuf.get(), codeBuf.get());
        classFile.setMinor_version(minorV);
        classFile.setMagor_version(majorV);
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(
                "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler.class");
        // 解析class文件
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        System.out.println(classFile.getMagic().toHexString());  // 打印魔数
        System.out.println(classFile.getMinor_version().toInt());  // 打印副版本号
        System.out.println(classFile.getMagor_version().toInt());  // 打印主版本号
    }
}
