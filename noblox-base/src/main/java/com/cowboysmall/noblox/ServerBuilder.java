package com.cowboysmall.noblox;

import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.handler.AcceptHandler;


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
        serverContext.getMasterReactor().invokeLater(reactor -> {

            Handle handle = reactor.registerAcceptor(acceptor);
            handle.setAttachment(new AcceptHandler(serverContext));
        });

        return new Server().withServerContext(serverContext);
    }
}
