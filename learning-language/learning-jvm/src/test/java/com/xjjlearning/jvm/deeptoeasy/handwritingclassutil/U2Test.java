package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil;

import com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type.U2;
import org.junit.Test;

/**
 * created by xjj on 2023/2/12
 */
public class U2Test {

    @Test
    public void f() {
        // 1111111 1111111 -> 65535 -> 2字节最大int
        U2 u = new U2((byte) 0xf0, (byte) 0xf0);
        System.out.println(u.toInt());
        System.out.println(u.toHexString());

        System.out.println((byte) 0x01); // 1
        System.out.println((byte) 0x0f); // 15
        System.out.println((byte) 0x1f); // 15 + 16 = 31
        System.out.println((byte) 0x7f); // 01111111 = 127
        // 最高位是1 有符号整数代表负数, 诸位取反后+1得到的数前面加一个负号
        // java不支持无符号类型, byte, short, int, long, 无符号只有封装
        System.out.println((byte) 0x80); // 10000000 -> -128
        System.out.println((byte) 0xf1); // 11110001 -> -15
        System.out.println((byte) 0xff); // 11111111 -> -1

        // 转换为无符号类型
        System.out.println(Byte.toUnsignedInt((byte) -10)); // 11110110 & 0xff -> 246 -> 255 -10 +1
        System.out.println((byte) -10 & 0xff); // -10先按照有符号转化成byte -> 11110110, 再与0xff相与, 得到无符号数
        System.out.println((int) -10 & 0xff); // 负数中int与byte互相转化
    }
}
