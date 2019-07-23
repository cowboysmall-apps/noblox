package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.WriteUpdate;


public class ReadHandler implements Handler, Runnable {

    private Handle handle;
    private ServerContext serverContext;
    private RequestContext requestContext;


    //_________________________________________________________________________

    public ReadHandler(Handle handle, ServerContext serverContext, RequestContext requestContext) {

        this.handle = handle;
        this.serverContext = serverContext;
        this.requestContext = requestContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            handle.setNoInterest();

            requestContext.getInput().append(
                    handle.getChannel().read(
                            serverContext.getReader()
                    )
            );

//            requestContext.getInput().append(
//                    serverContext.getReader().readFrom(
//                            handle.getChannel()
//                    )
//            );

            serverContext.getExecutor().execute(this);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        serverContext.getRequestHandler().handleRequest(requestContext);

        serverContext.getReactor().addReactorUpdate(
                new WriteUpdate(
                        handle,
                        new WriteHandler(handle, serverContext, requestContext)
                )
        );

        serverContext.getReactor().wakeup();
    }
}
