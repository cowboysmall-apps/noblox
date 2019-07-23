package com.cowboysmall.noblox.nio.io;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.io.Writer;
import com.cowboysmall.noblox.io.WriterException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class BasicNIOWriter implements Writer {

    @Override
    public void writeTo(byte[] bytes, Channel channel) {

        try {

            ((SocketChannel) channel.getChannel()).write(ByteBuffer.wrap(bytes));

        } catch (IOException e) {

            throw new WriterException(e);
        }
    }
}
