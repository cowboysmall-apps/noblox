package com.cowboysmall.noblox.nio.io;

import java.nio.ByteBuffer;


public class HeapNIOReader extends AbstractNIOReader {

    public HeapNIOReader(int capacity) {

        super(ByteBuffer.allocate(capacity));
    }

    public HeapNIOReader() {

        this(1024 * 4);
    }
}
