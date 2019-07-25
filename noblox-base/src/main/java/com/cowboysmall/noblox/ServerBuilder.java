package com.cowboysmall.noblox;

import com.cowboysmall.noblox.handler.AcceptHandler;


public class ServerBuilder {

    private ServerContext serverContext;


    //_________________________________________________________________________

    public ServerBuilder withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() {

        Acceptor acceptor = serverContext.getAcceptor();
        Reactor reactor = serverContext.getMasterReactor();

        reactor.invokeLater(r -> {

            Handle handle = r.registerAcceptor(acceptor);
            handle.setAttachment(new AcceptHandler(serverContext));
        });

        return new Server().withServerContext(serverContext);
    }
}
