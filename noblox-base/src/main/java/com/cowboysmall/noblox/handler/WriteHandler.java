package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;


public class WriteHandler implements Handler {

    private Handle handle;
    private ServerContext serverContext;
    private RequestContext requestContext;


    //_________________________________________________________________________

    public WriteHandler(Handle handle, ServerContext serverContext, RequestContext requestContext) {

        this.handle = handle;
        this.serverContext = serverContext;
        this.requestContext = requestContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            handle.setNoInterest();

            handle.getChannel().write(
                    requestContext.getOutput().getBytes(),
                    serverContext.getWriter()
            );

//            serverContext.getWriter().writeTo(
//                    requestContext.getOutput().getBytes(),
//                    handle.getChannel()
//            );

            handle.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
