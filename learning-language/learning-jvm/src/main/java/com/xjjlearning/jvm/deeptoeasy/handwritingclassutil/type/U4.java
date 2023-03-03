package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type;

/**
 * created by xjj on 2023/2/11
 */
public class U4 {
    private byte[] value;

    public U4(byte b1, byte b2, byte b3, byte b4) {
        value = new byte[]{b1, b2, b3, b4};
    }

    public Integer toInt() {
        return (value[0] & 0xff) << 24 | (value[1] & 0xff << 16) |
                (value[2] & 0xff << 8) | (value[3] & 0xff);
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
