package com.cowboysmall.noblox.nio.io;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.io.Reader;
import com.cowboysmall.noblox.io.ReaderException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public abstract class AbstractNIOReader implements Reader {

    private final ByteBuffer byteBuffer;


    //_________________________________________________________________________

    public AbstractNIOReader(ByteBuffer byteBuffer) {

        this.byteBuffer = byteBuffer;
    }


    //_________________________________________________________________________

    @Override
    public byte[] readFrom(Channel channel) {

        try {

            byteBuffer.clear();
            ((SocketChannel) channel.getChannel()).read(byteBuffer);
            byteBuffer.flip();

            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            return bytes;

        } catch (IOException e) {

            throw new ReaderException(e);
        }
    }
}
