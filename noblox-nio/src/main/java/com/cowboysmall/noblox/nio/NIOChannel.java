package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.buffer.InputBuffer;
import com.cowboysmall.noblox.buffer.OutputBuffer;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class NIOChannel implements Channel {

    private SocketChannel socketChannel;


    //_________________________________________________________________________

    public NIOChannel(SocketChannel socketChannel) {

        this.socketChannel = socketChannel;
    }


    //_________________________________________________________________________

    @Override
    public byte[] read(InputBuffer inputBuffer) throws IOException {

        return inputBuffer.readFrom(this);
    }

    @Override
    public void write(OutputBuffer outputBuffer) throws IOException {

        outputBuffer.writeTo(this);
    }

    @Override
    public Object getChannel() {

        return socketChannel;
    }
}
