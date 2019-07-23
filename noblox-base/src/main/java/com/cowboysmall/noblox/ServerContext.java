package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.Buffer;
import com.cowboysmall.noblox.io.Reader;
import com.cowboysmall.noblox.io.Writer;
import com.cowboysmall.noblox.thread.Executor;


public class ServerContext {

    private Acceptor acceptor;
    private Reactor reactor;
    private RequestHandler requestHandler;
    private Executor executor;

    private Reader reader;
    private Writer writer;

    private Class<? extends Buffer> bufferClass;


    //_________________________________________________________________________

    public Acceptor getAcceptor() {

        return acceptor;
    }

    public Reactor getReactor() {

        return reactor;
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

    public Buffer getBuffer() throws Exception {

        return bufferClass.newInstance();
    }


    //_________________________________________________________________________

    public ServerContext withAcceptor(Acceptor acceptor) {

        this.acceptor = acceptor;
        return this;
    }

    public ServerContext withDispatcher(Reactor Reactor) {

        this.reactor = Reactor;
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

    public ServerContext withBufferClass(Class<? extends Buffer> bufferClass) {

        this.bufferClass = bufferClass;
        return this;
    }
}
