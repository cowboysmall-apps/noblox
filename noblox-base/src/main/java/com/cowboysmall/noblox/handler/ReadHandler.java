package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class ReadHandler implements Handler {

    private ServerContext serverContext;
    private RequestContext requestContext;
    private Handle handle;


    //_________________________________________________________________________

    public ReadHandler(ServerContext serverContext, RequestContext requestContext, Handle handle) {

        this.serverContext = serverContext;
        this.requestContext = requestContext;
        this.handle = handle;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            Channel channel = handle.getChannel();
            handle.setNoInterest();
            requestContext.getInput().append(channel.read());

            if (channel.bufferFull()) {

                handle.setReadInterest();

            } else {

                serverContext.getRequestHandler().handleRequest(requestContext);
                handle.getReactor().invokeLater(reactor -> {

                    handle.setAttachment(new WriteHandler(requestContext, handle));
                    handle.setWriteInterest();
                });
            }

        } catch (Exception e) {

            throw new HandlerException(e);
        }
    }
}
