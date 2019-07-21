package com.cowboysmall.noblox;

import com.cowboysmall.noblox.dispatcher.Acceptor;
import com.cowboysmall.noblox.dispatcher.Key;
import com.cowboysmall.noblox.dispatcher.NIOAcceptor;
import com.cowboysmall.noblox.state.AcceptHandler;

import java.io.IOException;

public class ServerBuilder {

    private ServerContext serverContext;

    private String address;
    private int port;


    //_________________________________________________________________________

    public ServerBuilder withAddress(String address) {

        this.address = address;
        return this;
    }

    public ServerBuilder withPort(int port) {

        this.port = port;
        return this;
    }

    public ServerBuilder withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server build() throws IOException {

        Acceptor acceptor = new NIOAcceptor(address, port);

        Key key = serverContext.getDispatcher().registerAcceptInterest(acceptor);
        key.attach(new AcceptHandler(acceptor, serverContext));

        return new Server().withServerContext(serverContext);
    }
}
