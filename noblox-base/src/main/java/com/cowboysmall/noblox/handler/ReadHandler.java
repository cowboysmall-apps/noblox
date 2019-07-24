package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.*;


public class ReadHandler implements Handler {

    private Handle handle;
    private ServerContext serverContext;
    private RequestContext requestContext;


    //_________________________________________________________________________

    public ReadHandler(Handle handle, ServerContext serverContext, RequestContext requestContext) {

        this.handle = handle;
        this.serverContext = serverContext;
        this.requestContext = requestContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            Channel channel = handle.getChannel();
            Reactor reactor = handle.getReactor();

            handle.setNoInterest();
            requestContext.getInput().append(channel.read());
            serverContext.getRequestHandler().handleRequest(requestContext);

            reactor.addReactorUpdate(new WriteUpdate(handle, new WriteHandler(handle, requestContext)));

            reactor.wakeup();

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
