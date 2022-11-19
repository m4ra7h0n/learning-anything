package com.xjjlearning.java.util.concurrent;

import org.apache.http.nio.util.DirectByteBufferAllocator;

import java.nio.ByteBuffer;

public class Unsafe {
    private static final sun.misc.Unsafe unsafe = sun.misc.Unsafe.getUnsafe();;
    private static long valueOffset;
    private volatile int value;

    static {
        try{
            valueOffset = unsafe.objectFieldOffset(
                    Unsafe.class.getDeclaredField("value"));
        } catch (Exception ignore) {}
    }

    public void byteBuffer() {
        DirectByteBufferAllocator byteBufferAllocator = new DirectByteBufferAllocator();
        ByteBuffer allocate = byteBufferAllocator.allocate(1);
        unsafe.allocateMemory(1);
    }

    public static void main(String[] args) {
    }
}
