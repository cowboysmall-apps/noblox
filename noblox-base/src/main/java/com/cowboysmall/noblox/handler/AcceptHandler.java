package com.cowboysmall.noblox.handler;

import com.codahale.metrics.Meter;
import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class AcceptHandler implements Handler {

    private ServerContext serverContext;

    private Meter meter;


    //_________________________________________________________________________

    public AcceptHandler(ServerContext serverContext) {

        this.serverContext = serverContext;

        meter = serverContext.getMetricsRegistry().meter("AcceptHandler");
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            meter.mark();

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
