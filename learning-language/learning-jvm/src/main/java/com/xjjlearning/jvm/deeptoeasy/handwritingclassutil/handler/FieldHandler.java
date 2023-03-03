package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.*;

import java.nio.ByteBuffer;

/**
 * 字段处理器
 * 1、先从class文件字节缓存中读取到字段总数，根据字段总数创建字段表；
 * 2、循环解析出每个字段；
 * 3、解析字段的属性表时，先解析获取到属性总数，再根据属性总数创建属性表；
 * 4、使用通用属性结构循环解析出字段的每个属性；
 * 5、解析属性时，先解析出attribute_name_index，再解析attribute_length获取属性info的长度，根据长度读取指定长度的字节数据存放到属性的info字段。
 */
public class FieldHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 6;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {

        classFile.setFields_count(new U2(codeBuf.get(), codeBuf.get()));

        int len;
        if ((len = classFile.getFields_count().toInt()) == 0) {
            return;
        }

        int attrLen;
        // 创建字段表
        FieldInfo[] fieldInfos = new FieldInfo[len];
        classFile.setFields(fieldInfos);
        for (int i = 0; i < fieldInfos.length; i++) {
            // 解析字段
            if ((attrLen = analyseFieldInfo(fieldInfos, i, codeBuf)) == 0) {
                continue;
            }

            // 创建字段的属性表
            fieldInfos[i].setAttributes(new AttributeInfo[attrLen]);

            for (int j = 0; j < attrLen; j++) {
                fieldInfos[i].getAttributes()[j] = new AttributeInfo();
                // 解析属性
                analyseAttributes(fieldInfos, i, j, codeBuf);
            }
        }
    }

    private int analyseFieldInfo(FieldInfo[] fieldInfos, int fieldInfoIdx, ByteBuffer codeBuf) {
        // 解析字段
        fieldInfos[fieldInfoIdx] = new FieldInfo();
        // 读取字段的访问标志
        fieldInfos[fieldInfoIdx].setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
        // 读取字段名称
        fieldInfos[fieldInfoIdx].setName_index(new U2(codeBuf.get(), codeBuf.get()));
        // 读取字段类型描述符索引
        fieldInfos[fieldInfoIdx].setDescriptor_index(new U2(codeBuf.get(), codeBuf.get()));
        // 读取属性总数
        fieldInfos[fieldInfoIdx].setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        // 获取字段的属性总数
        return fieldInfos[fieldInfoIdx].getAttributes_count().toInt();
    }

    private void analyseAttributes(FieldInfo[] fieldInfos, int fieldInfoIdx, int attrIdx, ByteBuffer codeBuf) {
        // 解析字段的属性
        fieldInfos[fieldInfoIdx].getAttributes()[attrIdx].setAttribute_name_index(new U2(codeBuf.get(),codeBuf.get()));
        // 获取属性info的长度
        U4 attrInfoLen = new U4(codeBuf.get(), codeBuf.get(),  codeBuf.get(), codeBuf.get());
        fieldInfos[fieldInfoIdx].getAttributes()[attrIdx].setAttribute_length(attrInfoLen);
        // 解析info
        byte[] info = new byte[attrInfoLen.toInt()];
        codeBuf.get(info, 0, attrInfoLen.toInt());
        fieldInfos[fieldInfoIdx].getAttributes()[attrIdx].setInfo(info);
    }
}