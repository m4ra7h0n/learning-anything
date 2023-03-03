package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.*;

import java.nio.ByteBuffer;

public class MethodHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 7;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setMethods_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取方法总数
        int len = classFile.getMethods_count().toInt();
        if (len == 0) {
            return;
        }
        // 创建方法表
        MethodInfo[] methodInfos = new MethodInfo[len];
        classFile.setMethods(methodInfos);
        int attrLen;
        int attrInfoLen;
        for (int i = 0; i < methodInfos.length; i++) {
            // 解析方法
            if ((attrLen = analyseMethods(methodInfos, i, codeBuf)) == 0) {
                continue;
            }

            // 创建方法的属性表
            methodInfos[i].setAttributes(new AttributeInfo[attrLen]);

            for (int j = 0; j < attrLen; j++) {
                methodInfos[i].getAttributes()[j] = new AttributeInfo();
                // 解析属性表
                if ((attrInfoLen = analyseAttributes(methodInfos, i, j, codeBuf)) == 0) {
                    continue;
                }

                // 解析info
                byte[] info = new byte[attrInfoLen];
                codeBuf.get(info, 0, attrInfoLen);
                methodInfos[i].getAttributes()[j].setInfo(info);
            }
        }

    }
    private int analyseMethods(MethodInfo[] methodInfos, int i, ByteBuffer codeBuf) {
        // 解析方法
        methodInfos[i] = new MethodInfo();
        methodInfos[i].setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
        methodInfos[i].setName_index(new U2(codeBuf.get(), codeBuf.get()));
        methodInfos[i].setDescriptor_index(new U2(codeBuf.get(), codeBuf.get()));
        methodInfos[i].setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取方法的属性总数
        return methodInfos[i].getAttributes_count().toInt();
    }

    private int analyseAttributes(MethodInfo[] methodInfos, int methodInfoIdx, int attrIdx, ByteBuffer codeBuf) {
        methodInfos[methodInfoIdx].getAttributes()[attrIdx] = new AttributeInfo();
        // 解析方法的属性
        methodInfos[methodInfoIdx].getAttributes()[attrIdx]
                .setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
        // 获取属性info的长度
        U4 attrInfoLen = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
        methodInfos[methodInfoIdx].getAttributes()[attrIdx].setAttribute_length(attrInfoLen);
        return attrInfoLen.toInt();
    }
}