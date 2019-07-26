package com.cowboysmall.noblox.reactor.handler;

import com.codahale.metrics.Meter;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.request.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class WriteHandler implements Handler {

    private RequestContext requestContext;
    private Handle handle;

    private Meter meter;


    //_________________________________________________________________________

    public WriteHandler(ServerContext serverContext, RequestContext requestContext, Handle handle) {

        this.requestContext = requestContext;
        this.handle = handle;

        meter = serverContext.getMetricsRegistry().meter("WriteHandler");
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            meter.mark();

            Channel channel = handle.getChannel();
            handle.setNoInterest();
            channel.write(requestContext.getOutput().getBytes());
            handle.cancel();

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
