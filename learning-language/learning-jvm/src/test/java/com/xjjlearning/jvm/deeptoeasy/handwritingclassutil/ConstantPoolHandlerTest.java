package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.ClassFile;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.constantpool.CpInfo;
import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * created by xjj on 2023/2/12
 */
public class ConstantPoolHandlerTest {
    @Test
    public void testConstantPoolHandler() throws Exception {
        // 读取class文件，生成ByteBuffer
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(
                "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes/com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler.class");
        // 解析class文件
        ClassFile classFile = ClassFileAnalysiser.analyse(codeBuf);
        // 获取常量池常量的总数
        int cp_info_count = classFile.getConstant_pool_count().toInt();
        System.out.println("常量池中常量项总数：" + cp_info_count);
        // 遍历常量池中的常量
        CpInfo[] cpInfo = classFile.getConstant_pool();
        for (CpInfo cp : cpInfo) {
            System.out.println(cp.toString());
        }
    }
    /**
     * #1:tag=0x10
     * #2:tag=0x7
     * #3:tag=0x10
     * #4:tag=0x10
     * #5:tag=0x10
     * #6:tag=0x8
     * #7:tag=0x10
     * #8:tag=0x10
     * #9:tag=0x10
     * #10:tag=0x7
     * #11:tag=0x8
     * #12:tag=0x10
     * #13:tag=0x7
     * #14:tag=0x7
     * #15:tag=0x7
     * #16:tag=0x1,length=6,str=<init>
     * #17:tag=0x1,length=3,str=()V
     * #18:tag=0x1,length=4,str=Code
     * #19:tag=0x1,length=15,str=LineNumberTable
     * #20:tag=0x1,length=18,str=LocalVariableTable
     * #21:tag=0x1,length=4,str=this
     * #22:tag=0x1,length=74,str=Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler;
     * #23:tag=0x1,length=5,str=order
     * #24:tag=0x1,length=3,str=()I
     * #25:tag=0x1,length=4,str=read
     * #26:tag=0x1,length=92,str=(Ljava/nio/ByteBuffer;Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile;)V
     * #27:tag=0x1,length=7,str=codeBuf
     * #28:tag=0x1,length=21,str=Ljava/nio/ByteBuffer;
     * #29:tag=0x1,length=9,str=classFile
     * #30:tag=0x1,length=68,str=Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile;
     * #31:tag=0x1,length=13,str=StackMapTable
     * #32:tag=0x1,length=10,str=Exceptions
     * #33:tag=0x1,length=10,str=SourceFile
     * #34:tag=0x1,length=17,str=MagicHandler.java
     * #35:tag=0x12
     * #36:tag=0x1,length=59,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4
     * #37:tag=0x7
     * #38:tag=0x12
     * #39:tag=0x12
     * #40:tag=0x7
     * #41:tag=0x12
     * #42:tag=0x1,length=10,str=0xCAFEBABE
     * #43:tag=0x12
     * #44:tag=0x12
     * #45:tag=0x7
     * #46:tag=0x12
     * #47:tag=0x1,length=19,str=java/lang/Exception
     * #48:tag=0x1,length=18,str=Where is cafebaby?
     * #49:tag=0x12
     * #50:tag=0x1,length=72,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler
     * #51:tag=0x1,length=16,str=java/lang/Object
     * #52:tag=0x1,length=79,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/BaseByteCodeHandler
     * #53:tag=0x1,length=19,str=java/nio/ByteBuffer
     * #54:tag=0x1,length=3,str=get
     * #55:tag=0x1,length=3,str=()B
     * #56:tag=0x1,length=7,str=(BBBB)V
     * #57:tag=0x1,length=66,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile
     * #58:tag=0x1,length=8,str=setMagic
     * #59:tag=0x1,length=64,str=(Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4;)V
     * #60:tag=0x1,length=8,str=getMagic
     * #61:tag=0x1,length=63,str=()Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4;
     * #62:tag=0x1,length=11,str=toHexString
     * #63:tag=0x1,length=20,str=()Ljava/lang/String;
     * #64:tag=0x1,length=16,str=java/lang/String
     * #65:tag=0x1,length=16,str=equalsIgnoreCase
     * #66:tag=0x1,length=21,str=(Ljava/lang/String;)Z
     * #67:tag=0x1,length=21,str=(Ljava/lang/String;)V
     * 常量池中常量项总数：68
     * tag=0x10
     * tag=0x7
     * tag=0x10
     * tag=0x10
     * tag=0x10
     * tag=0x8
     * tag=0x10
     * tag=0x10
     * tag=0x10
     * tag=0x7
     * tag=0x8
     * tag=0x10
     * tag=0x7
     * tag=0x7
     * tag=0x7
     * tag=0x1,length=6,str=<init>
     * tag=0x1,length=3,str=()V
     * tag=0x1,length=4,str=Code
     * tag=0x1,length=15,str=LineNumberTable
     * tag=0x1,length=18,str=LocalVariableTable
     * tag=0x1,length=4,str=this
     * tag=0x1,length=74,str=Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler;
     * tag=0x1,length=5,str=order
     * tag=0x1,length=3,str=()I
     * tag=0x1,length=4,str=read
     * tag=0x1,length=92,str=(Ljava/nio/ByteBuffer;Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile;)V
     * tag=0x1,length=7,str=codeBuf
     * tag=0x1,length=21,str=Ljava/nio/ByteBuffer;
     * tag=0x1,length=9,str=classFile
     * tag=0x1,length=68,str=Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile;
     * tag=0x1,length=13,str=StackMapTable
     * tag=0x1,length=10,str=Exceptions
     * tag=0x1,length=10,str=SourceFile
     * tag=0x1,length=17,str=MagicHandler.java
     * tag=0x12
     * tag=0x1,length=59,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4
     * tag=0x7
     * tag=0x12
     * tag=0x12
     * tag=0x7
     * tag=0x12
     * tag=0x1,length=10,str=0xCAFEBABE
     * tag=0x12
     * tag=0x12
     * tag=0x7
     * tag=0x12
     * tag=0x1,length=19,str=java/lang/Exception
     * tag=0x1,length=18,str=Where is cafebaby?
     * tag=0x12
     * tag=0x1,length=72,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/MagicHandler
     * tag=0x1,length=16,str=java/lang/Object
     * tag=0x1,length=79,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/handler/BaseByteCodeHandler
     * tag=0x1,length=19,str=java/nio/ByteBuffer
     * tag=0x1,length=3,str=get
     * tag=0x1,length=3,str=()B
     * tag=0x1,length=7,str=(BBBB)V
     * tag=0x1,length=66,str=com/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/ClassFile
     * tag=0x1,length=8,str=setMagic
     * tag=0x1,length=64,str=(Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4;)V
     * tag=0x1,length=8,str=getMagic
     * tag=0x1,length=63,str=()Lcom/xjjlearning/jvm/deeptoeasy/handwritingclassutil/type/U4;
     * tag=0x1,length=11,str=toHexString
     * tag=0x1,length=20,str=()Ljava/lang/String;
     * tag=0x1,length=16,str=java/lang/String
     * tag=0x1,length=16,str=equalsIgnoreCase
     * tag=0x1,length=21,str=(Ljava/lang/String;)Z
     * tag=0x1,length=21,str=(Ljava/lang/String;)V
     *
     * Process finished with exit code 0
     */
}
