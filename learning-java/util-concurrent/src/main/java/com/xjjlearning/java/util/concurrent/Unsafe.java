package com.xjjlearning.java.util.concurrent;

import org.apache.http.nio.util.DirectByteBufferAllocator;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

public class Unsafe {
    private static long valueOffset;
    private volatile int value;
    private static long valuesOffset;
    private volatile Unsafe[] values;

    private static sun.misc.Unsafe U;
    private static long ABASE;
    private static int ASHIFT;

    static {
        try{
            U = sun.misc.Unsafe.getUnsafe();
            valueOffset = U.objectFieldOffset(
                    Unsafe.class.getDeclaredField("value"));

            Class<?> ak = Unsafe[].class;
            ABASE = U.arrayBaseOffset(ak);
            int scale = U.arrayIndexScale(ak);
            if ((scale & (scale - 1)) != 0)
                throw new Error("data type scale not a power of two");
            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
        } catch (Exception ignore) {}
    }

    public void byteBuffer() {
        DirectByteBufferAllocator byteBufferAllocator = new DirectByteBufferAllocator();
        ByteBuffer allocate = byteBufferAllocator.allocate(1);
        U.allocateMemory(1);
    }

    public static void main(String[] args) {
    }
}
