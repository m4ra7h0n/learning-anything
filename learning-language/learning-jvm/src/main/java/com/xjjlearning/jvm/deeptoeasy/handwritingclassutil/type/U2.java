package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type;

/**
 * created by xjj on 2023/2/11
 */
public class U2 {
    // 两个字节
    private byte[] value;

    public U2(byte b1, byte b2) {
        value = new byte[]{b1, b2};
    }

    public Integer toInt() {
        // &0xff 是为了限制大小, 并转化为无符号
        return (value[0] & 0xff) << 8 | (value[1] & 0xff);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public String toHexString() {
        char[] hexChars = new char[value.length * 2];
        for (int j = 0; j < value.length; j++) {
            int v = value[j] & 0xFF; // 限制大小, 并转化为无符号
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return "0x" + new String(hexChars);
    }

}
