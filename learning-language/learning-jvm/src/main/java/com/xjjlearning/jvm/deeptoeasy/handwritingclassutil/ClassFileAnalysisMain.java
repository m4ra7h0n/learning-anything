package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/11
 */
public class ClassFileAnalysisMain {
    public static ByteBuffer readFile(String classFilePath) throws Exception {
        File file = new File(classFilePath);
        if (!file.exists()) {
            throw new Exception("file not exists!");
        }
        byte[] byteCodeBuf = new byte[4096];
        int length;
        try (FileInputStream in = new FileInputStream(file)) {
            if ((length = in.read(byteCodeBuf)) < 1) {
                throw new Exception("not read byte code.");
            }
        }
        return ByteBuffer.wrap(byteCodeBuf, 0, length).asReadOnlyBuffer();
    }
    public static void main(String[] args) throws Exception {
        ByteBuffer codeBuf = readFile("xxx.class");
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        System.out.println(classFile.getMagic().toHexString());
    }
}
