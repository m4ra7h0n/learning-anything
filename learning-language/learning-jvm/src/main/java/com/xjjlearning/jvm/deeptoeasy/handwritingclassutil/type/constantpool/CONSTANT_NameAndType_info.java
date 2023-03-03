package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */

/**
 * 用于存储字段的名称和字段的类型描述符
 */
public class CONSTANT_NameAndType_info extends CpInfo{
    /**
     * 名称指向常量池中某个常量的索引, 必须是CONSTANT_Utf8_info
     */
    private U2 name_index;
    /**
     * 描述符指向常量池中某个常量的索引, 必须是CONSTANT_Utf8_info
     */
    private U2 descriptor_index;
    public CONSTANT_NameAndType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 名称索引
        name_index = new U2(codeBuf.get(), codeBuf.get());
        // 描述符索引
        descriptor_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
