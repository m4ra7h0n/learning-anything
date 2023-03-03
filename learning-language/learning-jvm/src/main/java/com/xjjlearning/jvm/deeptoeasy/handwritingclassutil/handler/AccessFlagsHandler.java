package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

public class AccessFlagsHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 3;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
    }
}
