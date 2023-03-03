package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

/**
 * created by xjj on 2023/2/12
 */

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * 表示invokedynamic指令用到的引导方法bootstrap method以及引导方法所用到的动态调用名称、参数、返回类型
 */
public class CONSTANT_InvokeDynamic_info extends CpInfo{
    /**
     * 指向class文件结构属性表中引导方法表的某个引导方法
     */
    private U2 bootstrap_method_attr_index;
    // 指向常量池中某个CONSTANT_NameAndType_Info结构的常量
    private U2 name_and_type_index;

    public CONSTANT_InvokeDynamic_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        bootstrap_method_attr_index = new U2(codeBuf.get(), codeBuf.get());
        name_and_type_index = new U2(codeBuf.get(), codeBuf.get());
    }
}
