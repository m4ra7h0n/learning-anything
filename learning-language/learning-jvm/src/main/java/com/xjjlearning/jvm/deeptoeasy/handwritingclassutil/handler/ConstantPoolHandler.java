package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Double_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CONSTANT_Long_info;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CpInfo;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */

/**
 * 常量池解析器, order = 2
 */
public class ConstantPoolHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 cpLen = new U2(codeBuf.get(), codeBuf.get());
        classFile.setConstant_pool_count(cpLen);
        classFile.setConstant_pool(new CpInfo[cpLen.toInt() - 1]);
        for (int i = 0; i < cpLen.toInt() - 1; i++) {
            U1 tag = new U1(codeBuf.get());
            CpInfo cpInfo = CpInfo.newCpInfo(tag);
            cpInfo.read(codeBuf);
            System.out.println("#" + (i + 1) + ":" + cpInfo);
            classFile.getConstant_pool()[i] = cpInfo;
            if (cpInfo instanceof CONSTANT_Long_info || cpInfo instanceof CONSTANT_Double_info) {
                i++; // jump n+2
            }
        }
    }
}
