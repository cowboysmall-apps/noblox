package com.cowboysmall.noblox;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.copyOf;

public class ChannelBuffer implements Channel, Buffer {

    private final ByteBuffer input = ByteBuffer.allocate(2048);

    private final List<ByteBuffer> output = new LinkedList<>();


    //_________________________________________________________________________

    @Override
    public void readFrom(SocketChannel socketChannel) throws IOException {

        socketChannel.read(input);
    }

    @Override
    public void writeTo(SocketChannel socketChannel) throws IOException {

        for (ByteBuffer byteBuffer : output)
            socketChannel.write(byteBuffer);
    }


    //_________________________________________________________________________

    @Override
    public byte[] bytesRead() {

        return copyOf(input.array(), input.position());
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
