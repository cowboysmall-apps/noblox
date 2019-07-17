package com.cowboysmall.noblox;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptManager implements ChannelManager {

    private SelectionKey selectionKey;
    private Server server;


    //_________________________________________________________________________

    public AcceptManager(SelectionKey selectionKey, Server server) {

        this.selectionKey = selectionKey;
        this.server = server;
    }


    //_________________________________________________________________________

    @Override
    public void execute() {

        try {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            SelectionKey selectionKey = server.getDispatcher().registerInterest(socketChannel, SelectionKey.OP_READ);
            selectionKey.attach(new ReadWriteManager(selectionKey, server));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
