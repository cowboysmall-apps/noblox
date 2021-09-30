package com.cowboysmall.noblox;

import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.handler.AcceptHandler;

public class Server {

    private ServerContext serverContext;


    //_________________________________________________________________________

    public ServerContext getServerContext() {

        return serverContext;
    }


    //_________________________________________________________________________

    public Server withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() {

        Acceptor acceptor = serverContext.getAcceptor();
        serverContext.getReactorManager().getMaster().invokeLater(reactor -> {

            Handle handle = acceptor.registerWith(reactor);
            handle.setAttachment(new AcceptHandler(serverContext));
        });

        return this;
    }


    //_________________________________________________________________________

    public Server start() {

        serverContext.getExecutor().execute(serverContext.getReactorManager().getMaster().start());

        for (Reactor slaveReactor : serverContext.getReactorManager().getSlaves())
            serverContext.getExecutor().execute(slaveReactor.start());

        return this;
    }

    public Server stop() {

        serverContext.getReactorManager().getMaster().stop();

        for (Reactor slaveReactor : serverContext.getReactorManager().getSlaves())
            slaveReactor.stop();

        return this;
    }
}
