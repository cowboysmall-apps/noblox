package com.cowboysmall.noblox;

import java.nio.channels.SelectionKey;

public class RequestHandlerAdapter implements Runnable {

    private Server server;
    private Dispatcher dispatcher;
    private RequestHandler requestHandler;

    private SelectionKey selectionKey;

    private byte[] input;


    //_________________________________________________________________________

    public RequestHandlerAdapter(Server server, Dispatcher dispatcher, RequestHandler requestHandler, SelectionKey selectionKey, byte[] input) {

        this.server = server;
        this.dispatcher = dispatcher;
        this.requestHandler = requestHandler;
        this.selectionKey = selectionKey;
        this.input = input;
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        server.addData(selectionKey, requestHandler.handleRequest(input));

        dispatcher.invokeLater(new DispatcherEvent(selectionKey, SelectionKey.OP_WRITE));
        dispatcher.wakeup();
    }
}
