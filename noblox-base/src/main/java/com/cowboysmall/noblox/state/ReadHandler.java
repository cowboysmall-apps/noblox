package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.channel.ChannelContext;
import com.cowboysmall.noblox.dispatcher.Channel;
import com.cowboysmall.noblox.dispatcher.DispatcherUpdate;
import com.cowboysmall.noblox.dispatcher.Key;
import com.cowboysmall.noblox.dispatcher.NIODispatcherUpdate;
import com.cowboysmall.noblox.memory.BasicNIOOutputBuffer;

import java.nio.channels.SelectionKey;

public class ReadHandler implements StateHandler, Runnable {

    private Key key;
    private ServerContext serverContext;

    private ChannelContext channelContext;


    //_________________________________________________________________________

    public ReadHandler(Key key, ServerContext serverContext) {

        this.key = key;
        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            key.interested(0);

            channelContext = new ChannelContext(serverContext.getInputBuffer(), new BasicNIOOutputBuffer());
            channelContext.readFrom(key.getChannel());

            serverContext.getExecutor().execute(this);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        serverContext.getRequestHandler().handleRequest(channelContext, channelContext.bytesRead());

        DispatcherUpdate dispatcherUpdate =
                new NIODispatcherUpdate(
                        (SelectionKey) key.getKey(),
                        SelectionKey.OP_WRITE,
                        new WriteHandler(key, channelContext)
                );

        serverContext.getDispatcher().addDispatcherUpdate(dispatcherUpdate);
        serverContext.getDispatcher().wakeup();
    }
}
