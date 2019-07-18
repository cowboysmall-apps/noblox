package com.cowboysmall.noblox.memory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public abstract class AbstractMemoryBuffer implements MemoryBuffer {

    private final ByteBuffer byteBuffer;


    //_________________________________________________________________________

    public AbstractMemoryBuffer(ByteBuffer byteBuffer) {

        this.byteBuffer = byteBuffer;
    }


    //_________________________________________________________________________

    @Override
    public byte[] readFrom(SocketChannel socketChannel) throws IOException {

        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }
}
