package com.cowboysmall.noblox.memory;

import java.nio.ByteBuffer;

public class HeapBufferPool extends AbstractBufferPool {

    public HeapBufferPool(int capacity) {

        super(ByteBuffer.allocate(capacity));
    }

    public HeapBufferPool() {

        this(1024 * 4);
    }
}
