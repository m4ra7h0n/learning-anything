package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.handler.*;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * created by xjj on 2023/2/11
 */
public class ClassFileAnalysiser {
    private final static List<BaseByteCodeHandler> handlers = new ArrayList<>();

    static {
        handlers.add(new MagicHandler());
        handlers.add(new VersionHandler());
        handlers.add(new ConstantPoolHandler());
        handlers.add(new AccessFlagsHandler());
        handlers.add(new ThisAndSuperClassHandler());
        handlers.add(new InterfacesHandler());
        handlers.add(new FieldHandler());
        handlers.add(new MethodHandler());
        handlers.add(new AttributesHandler());

        handlers.sort((Comparator.comparingInt(BaseByteCodeHandler::order)));
    }

    /**
     * 将传入的从class文件读取的字节缓存，解析生成一个ClassFile 对象
     *
     * @param codeBuf 字节缓存
     * @return 字节码文件
     * @throws Exception 读取某部分失败
     */
    public static ClassFile analyse(ByteBuffer codeBuf) throws Exception {
        // 重置ByteBuffer的读指针，从头开始(nio)
        codeBuf.position(0);
        ClassFile classFile = new ClassFile();
        // 不使用stream是为了抛出异常
        for (BaseByteCodeHandler handler : handlers) {
            handler.read(codeBuf, classFile);
        }
        return classFile;
    }
}
