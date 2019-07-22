package com.cowboysmall.noblox.nio.buffer;

import java.nio.ByteBuffer;


public class DirectNIOInputBuffer extends AbstractNIOInputBuffer {

    public DirectNIOInputBuffer(int capacity) {

        super(ByteBuffer.allocateDirect(capacity));
    }

    public DirectNIOInputBuffer() {

        this(1024 * 4);
    }
}
