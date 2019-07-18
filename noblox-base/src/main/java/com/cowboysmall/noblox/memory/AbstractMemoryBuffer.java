package com.cowboysmall.noblox.memory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class AbstractMemoryBuffer implements MemoryBuffer {

    protected final ByteBuffer byteBuffer;


    //_________________________________________________________________________

    public AbstractMemoryBuffer(ByteBuffer byteBuffer) {

        this.byteBuffer = byteBuffer;
    }


    //_________________________________________________________________________

    @Override
    public byte[] readFrom(SocketChannel socketChannel) throws IOException {

        socketChannel.read(byteBuffer);
        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }
}
