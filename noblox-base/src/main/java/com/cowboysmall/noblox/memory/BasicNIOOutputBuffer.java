package com.cowboysmall.noblox.memory;

import com.cowboysmall.noblox.dispatcher.Channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;


public class BasicNIOOutputBuffer implements OutputBuffer {

    private List<ByteBuffer> outputBuffers = new LinkedList<>();


    //_________________________________________________________________________

    @Override
    public void writeTo(Channel channel) throws IOException {

        for (ByteBuffer byteBuffer : outputBuffers)
            ((SocketChannel) channel.getChannel()).write(byteBuffer);
    }

    @Override
    public void append(byte[] bytes) {

        outputBuffers.add(ByteBuffer.wrap(bytes));
    }

    @Override
    public void append(String string) {

        append(string.getBytes());
    }
}
