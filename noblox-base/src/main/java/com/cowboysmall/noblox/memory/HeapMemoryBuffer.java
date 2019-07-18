package com.cowboysmall.noblox.memory;

import java.nio.ByteBuffer;

public class HeapMemoryBuffer extends AbstractMemoryBuffer {

    public HeapMemoryBuffer(int capacity) {

        super(ByteBuffer.allocate(capacity));
    }

    public HeapMemoryBuffer() {

        this(1024 * 4);
    }
}
