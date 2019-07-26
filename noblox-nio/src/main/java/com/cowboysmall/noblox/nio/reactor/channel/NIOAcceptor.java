package com.cowboysmall.noblox.nio.reactor.channel;

import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.channel.AcceptorException;
import com.cowboysmall.noblox.reactor.channel.Channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;


public class NIOAcceptor implements Acceptor {

    private final ServerSocketChannel serverSocketChannel;


    //_________________________________________________________________________

    public NIOAcceptor(String address, int port) throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(address, port));
    }


    //_________________________________________________________________________

    @Override
    public Object getAcceptor() {

        return serverSocketChannel;
    }

    @Override
    public Channel accept() {

        try {

            return new NIOChannel(serverSocketChannel.accept());

        } catch (Exception e) {

            throw new AcceptorException(e);
        }
    }
}
