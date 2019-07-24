package com.cowboysmall.noblox.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.Reactor;
import com.cowboysmall.noblox.RequestContext;


public class WriteHandler implements Handler {

    private Handle handle;
    private RequestContext requestContext;


    //_________________________________________________________________________

    public WriteHandler(Handle handle, RequestContext requestContext) {

        this.handle = handle;
        this.requestContext = requestContext;
    }


    //_________________________________________________________________________

    @Override
    public void handle() {

        try {

            Channel channel = handle.getChannel();
            Reactor reactor = handle.getReactor();

            handle.setNoInterest();
            channel.write(requestContext.getOutput().getBytes());
            reactor.unregisterInterest(handle);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
