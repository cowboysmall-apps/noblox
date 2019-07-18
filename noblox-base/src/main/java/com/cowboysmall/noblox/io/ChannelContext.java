package com.cowboysmall.noblox.io;

import com.cowboysmall.noblox.memory.BufferPool;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class ChannelContext implements ChannelWriter, ChannelBuffer {

    private List<ByteBuffer> output = new LinkedList<>();

    private BufferPool bufferPool;
    private byte[] bytesRead;


    //_________________________________________________________________________

    public ChannelContext(BufferPool bufferPool) {

        this.bufferPool = bufferPool;
    }


    //_________________________________________________________________________

    @Override
    public void readFrom(SocketChannel socketChannel) throws IOException {

        bytesRead = bufferPool.readFrom(socketChannel);
    }

    @Override
    public void writeTo(SocketChannel socketChannel) throws IOException {

        for (ByteBuffer byteBuffer : output)
            socketChannel.write(byteBuffer);
    }

    @Override
    public byte[] bytesRead() {

        return bytesRead;
    }


    //_________________________________________________________________________

    @Override
    public void write(ByteBuffer byteBuffer) {

        output.add(byteBuffer);
    }

    @Override
    public void write(byte[] bytes) {

        write(ByteBuffer.wrap(bytes));
    }

    @Override
    public void write(String string) {

        write(string.getBytes());
    }
}
