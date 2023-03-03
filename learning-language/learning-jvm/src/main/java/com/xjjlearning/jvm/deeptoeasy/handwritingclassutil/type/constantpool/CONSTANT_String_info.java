package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */
public class CONSTANT_String_info extends CpInfo {
    /**
     * 值为常量池中某个常量的索引，该索引指向的常量必须是一个CONSTANT_Utf8_info常量
     */
    private U2 string_index;

    public CONSTANT_String_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        string_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
