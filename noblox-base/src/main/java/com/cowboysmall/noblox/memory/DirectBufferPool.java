package com.cowboysmall.noblox.memory;

import java.nio.ByteBuffer;

public class DirectBufferPool extends AbstractBufferPool {

    public DirectBufferPool(int capacity) {

        super(ByteBuffer.allocateDirect(capacity));
    }

    public DirectBufferPool() {

        this(1024 * 4);
    }
}
