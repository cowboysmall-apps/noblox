package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.Context;
import com.cowboysmall.noblox.DispatcherUpdate;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.ServerContext;

import java.nio.channels.SelectionKey;

public class ReadHandler implements StateHandler, Runnable {

    private Handle handle;
    private ServerContext serverContext;

    private Context context;


    //_________________________________________________________________________

    public ReadHandler(Handle handle, ServerContext serverContext) {

        this.handle = handle;
        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            handle.setInterested(0);

            context = new Context(
                    serverContext.getInputBuffer().readFrom(handle.getChannel()),
                    serverContext.getOutputBuffer()
            );

            serverContext.getExecutor().execute(this);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        serverContext.getRequestHandler().handleRequest(context);

        serverContext.getDispatcher().addDispatcherUpdate(
                new DispatcherUpdate(
                        handle,
                        SelectionKey.OP_WRITE,
                        new WriteHandler(handle, context)
                )
        );

        serverContext.getDispatcher().wakeup();
    }
}
