package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */

/**
 * 用于存储方法句柄，这是虚拟机为实现动态调用指令（invokedynamic）所增加的常量结构
 */
public class CONSTANT_MethodHandle_info extends CpInfo{
    /**
     * [1, 9], 表示方法句柄的类型
     */
    private U1 reference_kind;
    /**
     * 值为指向常量池中某个常量的索引
     */
    private U2 reference_index;
    public CONSTANT_MethodHandle_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        reference_kind = new U1(codeBuf.get());
        reference_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
