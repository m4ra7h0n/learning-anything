package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */

/**
 * 存储类的符号信息
 */
@Getter
public class CONSTANT_Class_info extends CpInfo {
    /**
     * 存储的是类对应常量池中常量的索引, 一般是CONSTANT_Utf8_info常量
     */
    private U2 name_index;

    public CONSTANT_Class_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) {
        name_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
