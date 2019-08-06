package com.cowboysmall.noblox.nio.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.ChannelException;
import com.cowboysmall.noblox.reactor.channel.Handle;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;


public class NIOChannel implements Channel<SocketChannel> {

    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private final SocketChannel channel;

    private boolean bufferFull = false;


    //_________________________________________________________________________

    public NIOChannel(SocketChannel channel) {

        this.channel = channel;
    }


    //_________________________________________________________________________

    @Override
    public SocketChannel getImplementation() {

        return channel;
    }

    @Override
    public byte[] read() {

        try {

            channel.read(byteBuffer);
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

    @Override
    public void write(byte[] bytes) {

        try {

            channel.write(ByteBuffer.wrap(bytes));

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }

    @Override
    public boolean bufferFull() {

        return bufferFull;
    }

    @Override
    public Handle registerWith(Reactor reactor) {

        try {

            channel.configureBlocking(false);

            return new NIOHandle(
                    channel.register(
                            (Selector) reactor.getImplementation(),
                            SelectionKey.OP_READ
                    ),
                    this,
                    reactor
            );

        } catch (Exception e) {

            throw new ChannelException(e);
        }
    }
}
