package com.xjjlearning.jvm.deeptoeasy.handwritingclassutil.util;

public class HexStringUtils {

    public static String toHexString(byte[] codes) {
        StringBuilder codeStrBuild = new StringBuilder();
        int i = 0;
        for (byte code : codes) {
            // toHexString将字节转十六进制，实现略...
            codeStrBuild.append(toHexString(new byte[]{code})).append(" ");
            if (++i == 9) {
                i = 0;
                codeStrBuild.append("\n");
            }
        }
        return codeStrBuild.toString();
    }
}