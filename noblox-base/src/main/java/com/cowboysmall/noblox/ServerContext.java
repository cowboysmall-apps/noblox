package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.Buffer;
import com.cowboysmall.noblox.io.Reader;
import com.cowboysmall.noblox.io.Writer;
import com.cowboysmall.noblox.thread.Executor;

import java.util.Queue;


public class ServerContext {

    private Acceptor acceptor;
    private RequestHandler requestHandler;
    private Executor executor;

    private Reader reader;
    private Writer writer;

    private Reactor masterReactor;
    private Queue<Reactor> slaveReactors;

    private Class<? extends Buffer> bufferClass;


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

    public Reader getReader() {

        return reader;
    }

    public Writer getWriter() {

        return writer;
    }

    public Reactor getMasterReactor() {

        return masterReactor;
    }

    public Queue<Reactor> getSlaveReactors() {

        return slaveReactors;
    }

    public Buffer getBuffer() throws Exception {

        return bufferClass.newInstance();
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

    public ServerContext withReader(Reader reader) {

        this.reader = reader;
        return this;
    }

    public ServerContext withWriter(Writer writer) {

        this.writer = writer;
        return this;
    }

    public ServerContext withReactorFactory(ReactorFactory reactorFactory) throws Exception {

        this.masterReactor = reactorFactory.createMasterReactor();
        this.slaveReactors = reactorFactory.createSlaveReactors();
        return this;
    }

    public ServerContext withBufferClass(Class<? extends Buffer> bufferClass) {

        this.bufferClass = bufferClass;
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
