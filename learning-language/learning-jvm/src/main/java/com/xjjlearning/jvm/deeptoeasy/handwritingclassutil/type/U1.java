package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.type;

/**
 * created by xjj on 2023/2/12
 */
public class U1 {
    private final byte b;
    public U1(byte b1) {
        b = b1;
    }
    public Integer toInt() {
        return (b & 0xff);
    }
    public String toHexString() {
        return "0x" + (b & 0xff);
    }
}
