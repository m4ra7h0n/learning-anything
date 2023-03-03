package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/11
 */
public interface BaseByteCodeHandler {
    /**
     * 解释器的排序值
     * @return 排序值
     */
    int order();

    /**
     * 每个继承BaseByteCodeHandler的解析器都可以在read方法中从字节缓存读取相应的字节数据写入ClassFile对象
     * @param codeBuf class文件的字节缓存
     * @param classFile 待写入的ClassFile对象
     */
    void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception;
}
