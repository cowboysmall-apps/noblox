package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.memory.InputBuffer;
import com.cowboysmall.noblox.memory.OutputBuffer;

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
    public SocketChannel getChannel() {

        return socketChannel;
    }

    @Override
    public void close() throws IOException {

        socketChannel.close();
    }
}
