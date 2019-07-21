package com.cowboysmall.noblox.memory;

import java.nio.ByteBuffer;


public class HeapNIOInputBuffer extends AbstractNIOInputBuffer {

    public HeapNIOInputBuffer(int capacity) {

        super(ByteBuffer.allocate(capacity));
    }

    public HeapNIOInputBuffer() {

        this(1024 * 4);
    }
}
