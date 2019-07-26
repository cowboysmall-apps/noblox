package com.cowboysmall.noblox;

import com.codahale.metrics.MetricRegistry;
import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.ReactorFactory;
import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.request.RequestHandler;
import com.cowboysmall.noblox.thread.Executor;

import java.util.Queue;


public class ServerContext {

    private final MetricRegistry metricsRegistry = new MetricRegistry();

    private Acceptor acceptor;
    private RequestHandler requestHandler;
    private Executor executor;

    private Reactor masterReactor;
    private Queue<Reactor> slaveReactors;


    //_________________________________________________________________________

    public Acceptor getAcceptor() {

        return acceptor;
    }

    public RequestHandler getRequestHandler() {

        return requestHandler;
    }

    public Executor getExecutor() {

        return executor;
    }

    public Reactor getMasterReactor() {

        return masterReactor;
    }

    public Queue<Reactor> getSlaveReactors() {

        return slaveReactors;
    }

    public MetricRegistry getMetricsRegistry() {

        return metricsRegistry;
    }


    //_________________________________________________________________________

    public ServerContext withAcceptor(Acceptor acceptor) {

        this.acceptor = acceptor;
        return this;
    }

    public ServerContext withRequestHandler(RequestHandler requestHandler) {

        this.requestHandler = requestHandler;
        return this;
    }

    public ServerContext withExecutor(Executor executor) {

        this.executor = executor;
        return this;
    }

    public ServerContext withReactorFactory(ReactorFactory reactorFactory) throws Exception {

        this.masterReactor = reactorFactory.createMasterReactor();
        this.slaveReactors = reactorFactory.createSlaveReactors();
        return this;
    }


    //_________________________________________________________________________

    public Reactor getNextReactor() {

        Reactor next = slaveReactors.remove();
        slaveReactors.add(next);
        return next;
    }
}
