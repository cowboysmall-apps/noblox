package com.cowboysmall.noblox;

import com.codahale.metrics.MetricRegistry;
import com.cowboysmall.noblox.reactor.ReactorManager;
import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.request.RequestHandler;
import com.cowboysmall.noblox.thread.Executor;


public class ServerContext {

    private final MetricRegistry metricsRegistry = new MetricRegistry();

    private Acceptor acceptor;
    private RequestHandler requestHandler;
    private Executor executor;

    private ReactorManager reactorManager;


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

    public MetricRegistry getMetricsRegistry() {

        return metricsRegistry;
    }

    public ReactorManager getReactorManager() {

        return reactorManager;
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

    public ServerContext withReactorManager(ReactorManager reactorManager) {

        this.reactorManager = reactorManager;
        return this;
    }
}
