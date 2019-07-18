package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.dispatcher.DispatcherEvent;
import com.cowboysmall.noblox.io.ChannelContext;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReadHandler implements StateHandler, Runnable {

    private SelectionKey selectionKey;
    private Server server;
    private ChannelContext channelContext;


    //_________________________________________________________________________

    public ReadHandler(SelectionKey selectionKey, Server server, ChannelContext channelContext) {

        this.selectionKey = selectionKey;
        this.server = server;
        this.channelContext = channelContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            selectionKey.interestOps(0);

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            channelContext.readFrom(socketChannel);

            server.getExecutor().execute(this);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        server.getRequestHandler().handleRequest(channelContext, channelContext.bytesRead());

        DispatcherEvent dispatcherEvent =
                new DispatcherEvent(
                        selectionKey,
                        SelectionKey.OP_WRITE,
                        new WriteHandler(selectionKey, channelContext)
                );

        server.getDispatcher().invokeLater(dispatcherEvent);
        server.getDispatcher().wakeup();
    }
}
