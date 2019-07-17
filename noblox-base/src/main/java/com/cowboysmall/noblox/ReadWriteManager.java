package com.cowboysmall.noblox;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReadWriteManager implements ChannelManager, Runnable {

    private ChannelBuffer channelBuffer = new ChannelBuffer();

    private SelectionKey selectionKey;
    private Server server;


    //_________________________________________________________________________

    public ReadWriteManager(SelectionKey selectionKey, Server server) {

        this.selectionKey = selectionKey;
        this.server = server;
    }


    //_________________________________________________________________________

    @Override
    public void execute() {

        if (selectionKey.isReadable())
            readFrom();
        else if (selectionKey.isWritable())
            writeTo();
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        server.getRequestHandler().handleRequest(channelBuffer, channelBuffer.bytesRead());

        server.getDispatcher().invokeLater(new DispatcherEvent(selectionKey, SelectionKey.OP_WRITE));
        server.getDispatcher().wakeup();
    }


    //_________________________________________________________________________

    public void readFrom() {

        try {

            channelBuffer.readFrom((SocketChannel) selectionKey.channel());
            run();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void writeTo() {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            channelBuffer.writeTo(socketChannel);

            socketChannel.close();
            selectionKey.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
