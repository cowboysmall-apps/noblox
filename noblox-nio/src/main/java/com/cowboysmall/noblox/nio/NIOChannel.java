package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.ChannelException;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class NIOChannel implements Channel {

    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private final SocketChannel socketChannel;

    private boolean bufferFull = false;


    //_________________________________________________________________________

    public NIOChannel(SocketChannel socketChannel) {

        this.socketChannel = socketChannel;
    }


    //_________________________________________________________________________

    @Override
    public byte[] read() {

        try {

            socketChannel.read(byteBuffer);
            bufferFull = byteBuffer.remaining() == 0;

            byte[] bytes = new byte[byteBuffer.position()];

            byteBuffer.flip();
            byteBuffer.get(bytes);
            byteBuffer.clear();

            return bytes;

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    public boolean bufferFull() {

        return bufferFull;
    }

    @Override
    public void write(byte[] bytes) {

        try {

            socketChannel.write(ByteBuffer.wrap(bytes));

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    @Override
    public Object getChannel() {

        return socketChannel;
    }
}
