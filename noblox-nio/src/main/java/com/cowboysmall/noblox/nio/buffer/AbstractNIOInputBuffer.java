package com.cowboysmall.noblox.nio.buffer;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.buffer.InputBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public abstract class AbstractNIOInputBuffer implements InputBuffer {

    private final ByteBuffer byteBuffer;


    //_________________________________________________________________________

    public AbstractNIOInputBuffer(ByteBuffer byteBuffer) {

        this.byteBuffer = byteBuffer;
    }


    //_________________________________________________________________________

    @Override
    public byte[] readFrom(Channel channel) throws IOException {

        byteBuffer.clear();
        ((SocketChannel) channel.getChannel()).read(byteBuffer);
        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }
}
