package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.ChannelException;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class NIOChannel implements Channel {

    private SocketChannel socketChannel;

    private int bytesRead;


    //_________________________________________________________________________

    public NIOChannel(SocketChannel socketChannel) {

        this.socketChannel = socketChannel;
    }


    //_________________________________________________________________________

    @Override
    public byte[] read() {

        try {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            bytesRead = socketChannel.read(byteBuffer);
            byteBuffer.flip();

            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            return bytes;

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    @Override
    public int bytesRead() {

        return bytesRead;
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
