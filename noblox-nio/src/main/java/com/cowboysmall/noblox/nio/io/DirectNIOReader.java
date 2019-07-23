package com.cowboysmall.noblox.nio.io;

import java.nio.ByteBuffer;


public class DirectNIOReader extends AbstractNIOReader {

    public DirectNIOReader(int capacity) {

        super(ByteBuffer.allocateDirect(capacity));
    }

    public DirectNIOReader() {

        this(1024 * 4);
    }
}
