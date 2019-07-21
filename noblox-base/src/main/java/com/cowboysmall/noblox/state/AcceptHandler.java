package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.dispatcher.Acceptor;
import com.cowboysmall.noblox.dispatcher.Channel;
import com.cowboysmall.noblox.dispatcher.Key;

public class AcceptHandler implements StateHandler {

    private Acceptor acceptor;
    private ServerContext serverContext;


    //_________________________________________________________________________

    public AcceptHandler(Acceptor acceptor, ServerContext serverContext) {

        this.acceptor = acceptor;
        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            Channel channel = acceptor.accept();

            Key key = serverContext.getDispatcher().registerReadInterest(channel);
            key.attach(new ReadHandler(key, serverContext));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
