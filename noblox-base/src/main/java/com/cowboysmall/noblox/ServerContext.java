package com.cowboysmall.noblox;

import com.cowboysmall.noblox.dispatcher.Dispatcher;
import com.cowboysmall.noblox.memory.MemoryBuffer;
import com.cowboysmall.noblox.request.RequestHandler;
import com.cowboysmall.noblox.thread.Executor;

public class ServerContext {

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;
    private Executor executor;
    private MemoryBuffer memoryBuffer;

    //_________________________________________________________________________

    public Dispatcher getDispatcher() {

        return dispatcher;
    }

    public RequestHandler getRequestHandler() {

        return requestHandler;
    }

    public Executor getExecutor() {

        return executor;
    }

    public MemoryBuffer getMemoryBuffer() {

        return memoryBuffer;
    }


    //_________________________________________________________________________

    public ServerContext withDispatcher(Dispatcher dispatcher) {

        this.dispatcher = dispatcher;
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

    public ServerContext withMemoryBuffer(MemoryBuffer memoryBuffer) {

        this.memoryBuffer = memoryBuffer;
        return this;
    }
}
