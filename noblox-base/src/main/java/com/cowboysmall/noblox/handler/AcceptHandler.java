package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class AcceptHandler implements Handler {

    private ServerContext serverContext;


    //_________________________________________________________________________

    public AcceptHandler(ServerContext serverContext) {

        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            Channel channel = serverContext.getAcceptor().accept();

            Handle handle = serverContext.getReactor().registerReadInterest(channel);
            handle.setAttachment(
                    new ReadHandler(
                            handle,
                            serverContext,
                            new RequestContext(
                                    serverContext.getBuffer(),
                                    serverContext.getBuffer()
                            )
                    )
            );

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
