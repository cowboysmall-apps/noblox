package com.cowboysmall.noblox;

import com.cowboysmall.noblox.state.AcceptHandler;

import java.io.IOException;

public class ServerBuilder {

    private Acceptor acceptor;

    private ServerContext serverContext;


    //_________________________________________________________________________

    public ServerBuilder withAcceptor(Acceptor acceptor) {

        this.acceptor = acceptor;
        return this;
    }

    public ServerBuilder withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() throws IOException {

        Handle handle = serverContext.getDispatcher().registerAcceptInterest(acceptor);
        handle.setAttachment(new AcceptHandler(acceptor, serverContext));

        return new Server().withServerContext(serverContext);
    }
}
