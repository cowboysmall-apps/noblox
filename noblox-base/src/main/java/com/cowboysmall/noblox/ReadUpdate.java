package com.cowboysmall.noblox;

import com.cowboysmall.noblox.handler.Handler;


public class ReadUpdate implements ReactorUpdate {

    private Handle handle;
    private Handler handler;


    //_________________________________________________________________________

    public ReadUpdate(Handle handle, Handler handler) {

        this.handle = handle;
        this.handler = handler;
    }


    //_________________________________________________________________________

    @Override
    public void apply() {

        handle.setAttachment(handler);
        handle.setReadInterest();
    }
}
