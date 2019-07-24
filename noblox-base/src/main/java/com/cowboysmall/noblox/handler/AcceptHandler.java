package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.*;


public class AcceptHandler implements Handler {

    private ServerContext serverContext;


    //_________________________________________________________________________

    public AcceptHandler(ServerContext serverContext) {

        this.serverContext = serverContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            Acceptor acceptor = serverContext.getAcceptor();
            Channel channel = acceptor.accept();
            Reactor reactor = serverContext.getNextReactor();

            reactor.lock();
            try {

                reactor.wakeup();

                Handle handle = reactor.registerReadInterest(channel);
                handle.setAttachment(new ReadHandler(handle, serverContext, new RequestContext()));

            } finally {

                reactor.unlock();
            }

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
