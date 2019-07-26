package com.cowboysmall.noblox.reactor.handler;

import com.codahale.metrics.Meter;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.request.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class ReadHandler implements Handler {

    private ServerContext serverContext;
    private RequestContext requestContext;
    private Handle handle;

    private Meter meter;


    //_________________________________________________________________________

    public ReadHandler(ServerContext serverContext, RequestContext requestContext, Handle handle) {

        this.serverContext = serverContext;
        this.requestContext = requestContext;
        this.handle = handle;

        meter = serverContext.getMetricsRegistry().meter("ReadHandler");
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            meter.mark();

            Channel channel = handle.getChannel();
            handle.setNoInterest();
            requestContext.getInput().append(channel.read());

            if (channel.bufferFull()) {

                handle.setReadInterest();

            } else {

                serverContext.getRequestHandler().handleRequest(requestContext);
                handle.getReactor().invokeLater(reactor -> {

                    handle.setAttachment(new WriteHandler(serverContext, requestContext, handle));
                    handle.setWriteInterest();
                });
            }

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
