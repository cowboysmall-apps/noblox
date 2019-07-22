package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.InputBuffer;
import com.cowboysmall.noblox.buffer.OutputBuffer;
import com.cowboysmall.noblox.thread.Executor;

public class ServerContext {

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;
    private Executor executor;

    private InputBuffer inputBuffer;
    private Class<? extends OutputBuffer> outputBufferClass;


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

    public OutputBuffer getOutputBuffer() throws Exception {

        return outputBufferClass.newInstance();
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

    public ServerContext withInputBuffer(InputBuffer inputBuffer) {

        this.inputBuffer = inputBuffer;
        return this;
    }

    public ServerContext withOutputBufferClass(Class<? extends OutputBuffer> outputBufferClass) {

        this.outputBufferClass = outputBufferClass;
        return this;
    }
}
