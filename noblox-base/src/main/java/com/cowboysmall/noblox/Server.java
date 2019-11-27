package com.cowboysmall.noblox;

import com.cowboysmall.noblox.reactor.Reactor;


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

    public Server start() {

        serverContext.getExecutor().execute(serverContext.getMasterReactor().start());

        for (Reactor slaveReactor : serverContext.getSlaveReactors())
            serverContext.getExecutor().execute(slaveReactor.start());

        return this;
    }

    public Server stop() {

        serverContext.getMasterReactor().stop();

        for (Reactor slaveReactor : serverContext.getSlaveReactors())
            slaveReactor.stop();

        return this;
    }
}
