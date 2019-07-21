package com.cowboysmall.noblox;

import com.cowboysmall.noblox.dispatcher.Dispatcher;
import com.cowboysmall.noblox.memory.InputBuffer;
import com.cowboysmall.noblox.request.RequestHandler;
import com.cowboysmall.noblox.thread.Executor;

public class ServerContext {

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;
    private Executor executor;
    private InputBuffer inputBuffer;


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

    public InputBuffer getInputBuffer() {

        return inputBuffer;
    }


    //_________________________________________________________________________

    public ServerContext withDispatcher(Dispatcher Dispatcher) {

        this.dispatcher = Dispatcher;
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

    public ServerContext withMemoryBuffer(InputBuffer inputBuffer) {

        this.inputBuffer = inputBuffer;
        return this;
    }
}
