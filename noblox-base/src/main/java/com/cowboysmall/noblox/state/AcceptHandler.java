package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.channel.ChannelContext;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements StateHandler {

    private SelectionKey selectionKey;
    private ServerContext serverContext;


    //_________________________________________________________________________

    public AcceptHandler(SelectionKey selectionKey, ServerContext serverContext) {

        this.selectionKey = selectionKey;
        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            SelectionKey selectionKey = serverContext.getDispatcher().registerInterest(socketChannel, SelectionKey.OP_READ);
            selectionKey.attach(new ReadHandler(selectionKey, serverContext));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
