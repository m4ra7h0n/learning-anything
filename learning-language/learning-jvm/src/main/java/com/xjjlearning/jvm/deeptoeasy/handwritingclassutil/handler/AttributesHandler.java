package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.AttributeInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U4;

import java.nio.ByteBuffer;

/**
 * 解析class文件的属性表
 */
public class AttributesHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 8;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取属性总数
        int len;
        if ((len = classFile.getAttributes_count().toInt()) == 0) {
            return;
        }
        // 创建属性表
        AttributeInfo[] attributeInfos = new AttributeInfo[len];
        classFile.setAttributes(attributeInfos);
        int attrLen;
        for (int i = 0; i < len; i++) {
            AttributeInfo attributeInfo = analyseClasses(attributeInfos, i, codeBuf);
            if ((attrLen = attributeInfo.getAttribute_length().toInt()) == 0) {
                continue;
            }
            // 解析属性的info项
            byte[] bytes = new byte[attrLen];
            codeBuf.get(bytes, 0, bytes.length);
            attributeInfo.setInfo(bytes);
        }
    }

    private AttributeInfo analyseClasses(AttributeInfo[] attributeInfos, int attrIdx, ByteBuffer codeBuf) {
        // 创建属性
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfos[attrIdx] = attributeInfo;
        // 解析属性
        attributeInfo.setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
        attributeInfo.setAttribute_length(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
        return attributeInfo;
    }
}