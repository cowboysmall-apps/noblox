package com.cowboysmall.noblox;

import com.cowboysmall.noblox.state.AcceptHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class ServerBuilder {

    private ServerContext serverContext;

    private String address;
    private int port;


    //_________________________________________________________________________

    public ServerBuilder withAddress(String address) {

        this.address = address;
        return this;
    }

    public ServerBuilder withPort(int port) {

        this.port = port;
        return this;
    }

    public ServerBuilder withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(address, port));

        SelectionKey selectionKey =
                serverContext.getDispatcher().registerInterest(serverSocketChannel, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler(selectionKey, serverContext));

        return new Server().withServerContext(serverContext);
    }
}
