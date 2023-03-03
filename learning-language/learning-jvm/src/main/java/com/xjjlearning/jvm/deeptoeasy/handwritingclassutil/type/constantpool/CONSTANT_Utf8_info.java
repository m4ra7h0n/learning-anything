package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U1;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 用于存储字符串常量，字符串编码使用UTF-8
 */
public class CONSTANT_Utf8_info extends CpInfo {

    private U2 length;
    private byte[] bytes;

    public CONSTANT_Utf8_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) {
        // 先读取长度
        length = new U2(codeBuf.get(), codeBuf.get());
        bytes = new byte[length.toInt()];
        // 读取指定长度的字节到bytes 数组
        codeBuf.get(bytes, 0, length.toInt());
    }

    @Override
    public String toString() {
        return super.toString() + ",length=" + length.toInt() + ",str=" + new String(bytes, StandardCharsets.UTF_8);
    }
}