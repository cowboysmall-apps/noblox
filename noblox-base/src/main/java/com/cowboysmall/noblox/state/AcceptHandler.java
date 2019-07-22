package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.Acceptor;
import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;

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

            Handle handle = serverContext.getDispatcher().registerReadInterest(channel);
            handle.setAttachment(new ReadHandler(handle, serverContext));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
