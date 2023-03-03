package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */

/**
 * 表示方法类型
 * 与CONSTANT_MethodHandle_info结构一样，也是虚拟机为实现动态调用invokedynamic指令所增加的常量结构
 */
public class CONSTANT_MethodType_info extends CpInfo{
    /**
     * 指向常量池中的某一CONSTANT_Utf8_info结构的常量
     */
    private U2 descriptor_index;
    public CONSTANT_MethodType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        descriptor_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
