package com.cowboysmall.noblox;

import com.cowboysmall.noblox.handler.AcceptHandler;

import java.io.IOException;


public class ServerBuilder {

    private ServerContext serverContext;


    //_________________________________________________________________________

    public ServerBuilder withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() {

        Handle handle = serverContext.getReactor().registerAcceptInterest(serverContext.getAcceptor());
        handle.setAttachment(new AcceptHandler(serverContext));

        return new Server().withServerContext(serverContext);
    }
}
