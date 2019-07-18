package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.io.ChannelContext;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements StateHandler {

    private SelectionKey selectionKey;
    private Server server;


    //_________________________________________________________________________

    public AcceptHandler(SelectionKey selectionKey, Server server) {

        this.selectionKey = selectionKey;
        this.server = server;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            SelectionKey selectionKey = server.getDispatcher().registerInterest(socketChannel, SelectionKey.OP_READ);
            selectionKey.attach(new ReadHandler(selectionKey, server, new ChannelContext(server.getBufferPool())));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
