package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */
public class CONSTANT_InterfaceMethodref_info extends CpInfo{
    /**
     * class_index：指向的常量必须是一个CONSTANT_Class_Info常量，表示当前字段所在类的类名；
     */
    private U2 class_index;
    /**
     * name_and_type_inde：指向的常量必须是一个CONSTANT_NameAndType_info常量，表示当前字段的名字和类型描述符。
     */
    private U2 name_and_type_index;

    public CONSTANT_InterfaceMethodref_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        class_index = new U2(codeBuf.get(), codeBuf.get());
        name_and_type_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
