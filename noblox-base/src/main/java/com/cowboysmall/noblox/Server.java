package com.cowboysmall.noblox;

import com.codahale.metrics.ConsoleReporter;
import com.cowboysmall.noblox.reactor.Reactor;

import java.util.concurrent.TimeUnit;


public class Server {

    private ServerContext serverContext;

//    private ConsoleReporter consoleReporter;


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

//        consoleReporter =
//                ConsoleReporter.forRegistry(serverContext.getMetricsRegistry())
//                        .convertRatesTo(TimeUnit.SECONDS)
//                        .convertDurationsTo(TimeUnit.MILLISECONDS)
//                        .build();

//        consoleReporter.start(5, TimeUnit.SECONDS);

        return this;
    }

    public Server stop() {

        serverContext.getMasterReactor().stop();

        for (Reactor slaveReactor : serverContext.getSlaveReactors())
            slaveReactor.stop();

//        consoleReporter.stop();

        return this;
    }
}
