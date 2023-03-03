package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;

/**
 * 在class文件中，继this_class与super_class之后，存储的就是该class实现的接口总数以及该class实现的所有接口。
 */
public class InterfacesHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 5;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setInterfaces_count(new U2(codeBuf.get(), codeBuf.get()));
        Integer interfacesCount = classFile.getInterfaces_count().toInt();
        U2[] interfaces = new U2[interfacesCount];
        classFile.setInterfaces(interfaces);

        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = new U2(codeBuf.get(), codeBuf.get());
        }
    }
}