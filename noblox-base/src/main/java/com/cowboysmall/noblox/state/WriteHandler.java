package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.Context;
import com.cowboysmall.noblox.Handle;

public class WriteHandler implements StateHandler {

    private Handle handle;
    private Context context;


    //_________________________________________________________________________

    public WriteHandler(Handle handle, Context context) {

        this.handle = handle;
        this.context = context;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            handle.setInterested(0);

            context.getOutputBuffer().writeTo(handle.getChannel());
            handle.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
