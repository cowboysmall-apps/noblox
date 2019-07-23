package com.cowboysmall.noblox;

import com.cowboysmall.noblox.handler.Handler;


public class WriteUpdate implements ReactorUpdate {

    private Handle handle;
    private Handler handler;


    //_________________________________________________________________________

    public WriteUpdate(Handle handle, Handler handler) {

        this.handle = handle;
        this.handler = handler;
    }


    //_________________________________________________________________________

    @Override
    public void apply() {

        handle.setAttachment(handler);
        handle.setWriteInterest();
    }
}
