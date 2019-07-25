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
            serverContext.getNextReactor().invokeNow(reactor -> {

                Handle handle = reactor.registerChannel(channel);
                handle.setAttachment(new ReadHandler(serverContext, new RequestContext(), handle));
            });

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
