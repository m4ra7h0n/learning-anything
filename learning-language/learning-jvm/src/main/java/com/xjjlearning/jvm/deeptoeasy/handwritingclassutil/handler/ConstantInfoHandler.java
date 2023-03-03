package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */
public interface ConstantInfoHandler {

    /**
     * 每个继承ConstantInfoHandler的解析器都可以在read方法中从字节缓存读取相应的字节数据写入ClassFile对象
     * @param codeBuf class文件的字节缓存
     */
    void read(ByteBuffer codeBuf) throws Exception;
}
