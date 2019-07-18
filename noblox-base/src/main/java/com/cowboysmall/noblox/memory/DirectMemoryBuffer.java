package com.cowboysmall.noblox.memory;

import java.nio.ByteBuffer;

public class DirectMemoryBuffer extends AbstractMemoryBuffer {

    public DirectMemoryBuffer(int capacity) {

        super(ByteBuffer.allocateDirect(capacity));
    }

    public DirectMemoryBuffer() {

        this(1024 * 4);
    }
}
