package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * this_class存储的是常量池中某项常量的索引
 * super_class要么为0，要么也是存储常量池中某项常量的索引1。this_class和super_class指向的常量必须是一个CONSTANT_Class_info结构的常量。
 * 只有Object类的super_class可以为0，接口的super_class指向常量池中描述Object类的CONSTANT_Class_info常量。
 */
public class ThisAndSuperClassHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 4;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setThis_class(new U2(codeBuf.get(), codeBuf.get()));
        classFile.setSuper_class(new U2(codeBuf.get(), codeBuf.get()));
    }
}