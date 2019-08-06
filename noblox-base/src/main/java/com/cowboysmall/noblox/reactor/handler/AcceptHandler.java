package com.cowboysmall.noblox.reactor.handler;

import com.codahale.metrics.Meter;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.request.RequestContext;


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

                Handle handle = channel.registerWith(reactor);
                handle.setAttachment(new ReadHandler(serverContext, new RequestContext(), handle));
            });

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
