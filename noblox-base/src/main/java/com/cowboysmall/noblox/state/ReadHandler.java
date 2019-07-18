package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.channel.ChannelContext;
import com.cowboysmall.noblox.dispatcher.DispatcherEvent;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ReadHandler implements StateHandler, Runnable {

    private SelectionKey selectionKey;
    private ServerContext serverContext;

    private ChannelContext channelContext;


    //_________________________________________________________________________

    public ReadHandler(SelectionKey selectionKey, ServerContext serverContext) {

        this.selectionKey = selectionKey;
        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            selectionKey.interestOps(0);
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            channelContext = new ChannelContext(serverContext.getMemoryBuffer());
            channelContext.readFrom(socketChannel);

            serverContext.getExecutor().execute(this);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        serverContext.getRequestHandler().handleRequest(channelContext, channelContext.bytesRead());

        DispatcherEvent dispatcherEvent =
                new DispatcherEvent(
                        selectionKey,
                        SelectionKey.OP_WRITE,
                        new WriteHandler(selectionKey, channelContext)
                );

        serverContext.getDispatcher().addDispatcherEvent(dispatcherEvent);
        serverContext.getDispatcher().wakeup();
    }
}
