package com.cowboysmall.noblox.nio.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.channel.AcceptorException;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;


public class NIOAcceptor implements Acceptor<ServerSocketChannel> {

    private final ServerSocketChannel channel;


    //_________________________________________________________________________

    public NIOAcceptor(String address, int port) throws IOException {

        channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(address, port));
    }


    //_________________________________________________________________________

    @Override
    public ServerSocketChannel getImplementation() {

        return channel;
    }

    @Override
    public Channel accept() {

        try {

            return new NIOChannel(channel.accept());

        } catch (Exception e) {

            throw new AcceptorException(e);
        }
    }

    @Override
    public Handle registerWith(Reactor reactor) {

        try {

            channel.configureBlocking(false);

            return new NIOHandle(
                    channel.register(
                            (Selector) reactor.getImplementation(),
                            SelectionKey.OP_ACCEPT
                    ),
                    null,
                    reactor
            );

        } catch (Exception e) {

            throw new AcceptorException(e);
        }
    }
}
