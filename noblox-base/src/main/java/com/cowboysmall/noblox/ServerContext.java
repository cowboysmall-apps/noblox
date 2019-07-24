package com.cowboysmall.noblox;

import com.cowboysmall.noblox.thread.Executor;

import java.util.Queue;


public class ServerContext {

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

        if (slaveReactors.isEmpty())
            return masterReactor;

        Reactor next = slaveReactors.remove();
        slaveReactors.add(next);
        return next;
    }
}
