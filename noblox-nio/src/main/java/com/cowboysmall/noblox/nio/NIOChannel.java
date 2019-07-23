package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.ChannelException;
import com.cowboysmall.noblox.io.Reader;
import com.cowboysmall.noblox.io.Writer;

import java.nio.channels.SocketChannel;


public class NIOChannel implements Channel {

    private SocketChannel socketChannel;


    //_________________________________________________________________________

    public NIOChannel(SocketChannel socketChannel) {

        this.socketChannel = socketChannel;
    }


    //_________________________________________________________________________

    @Override
    public byte[] read(Reader reader) {

        try {

            return reader.readFrom(this);

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    @Override
    public void write(byte[] bytes, Writer writer) {

        try {

            writer.writeTo(bytes, this);

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    @Override
    public Object getChannel() {

        return socketChannel;
    }
}
