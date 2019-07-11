package com.cowboysmall.noblox.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static java.util.Arrays.copyOf;

public class DataBuffer {

    private final ByteBuffer buffer;


    //_________________________________________________________________________

    public DataBuffer(int capacity) {

        buffer = ByteBuffer.allocate(capacity);
    }

    public DataBuffer() {

        this(2048);
    }


    //_________________________________________________________________________

    public byte[] readFrom(SocketChannel socketChannel) throws IOException {

        buffer.clear();
        socketChannel.read(buffer);
        return copyOf(buffer.array(), buffer.position());
    }
}
