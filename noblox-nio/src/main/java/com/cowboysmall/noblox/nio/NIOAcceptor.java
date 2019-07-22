package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Acceptor;
import com.cowboysmall.noblox.Channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class NIOAcceptor implements Acceptor {

    private ServerSocketChannel serverSocketChannel;


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
    public Channel accept() throws IOException {

        return new NIOChannel(serverSocketChannel.accept());
    }
}
