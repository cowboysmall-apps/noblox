package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.Reactor;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.WriteUpdate;


public class ReadHandler implements Handler {

    private ServerContext serverContext;
    private RequestContext requestContext;
    private Handle handle;


    //_________________________________________________________________________

    public ReadHandler(ServerContext serverContext, RequestContext requestContext, Handle handle) {

        this.serverContext = serverContext;
        this.requestContext = requestContext;
        this.handle = handle;
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

            reactor.addReactorUpdate(new WriteUpdate(handle, new WriteHandler(requestContext, handle)));
            reactor.wakeup();

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
