package com.cowboysmall.noblox.channel;

import com.cowboysmall.noblox.memory.MemoryBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class ChannelContext implements ChannelWriter, ChannelBuffer {

    private List<ByteBuffer> output = new LinkedList<>();

    private MemoryBuffer memoryBuffer;
    private byte[] bytesRead;


    //_________________________________________________________________________

    public ChannelContext(MemoryBuffer memoryBuffer) {

        this.memoryBuffer = memoryBuffer;
    }


    //_________________________________________________________________________

    @Override
    public void readFrom(SocketChannel socketChannel) throws IOException {

        bytesRead = memoryBuffer.readFrom(socketChannel);
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
