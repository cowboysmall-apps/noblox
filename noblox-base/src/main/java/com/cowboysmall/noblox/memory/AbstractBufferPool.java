package com.cowboysmall.noblox.memory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class AbstractBufferPool implements BufferPool {

    protected final ByteBuffer buffer;


    //_________________________________________________________________________

    public AbstractBufferPool(ByteBuffer buffer) {

        this.buffer = buffer;
    }


    //_________________________________________________________________________

    @Override
    public byte[] readFrom(SocketChannel socketChannel) throws IOException {

        socketChannel.read(buffer);
        buffer.flip();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }
}
