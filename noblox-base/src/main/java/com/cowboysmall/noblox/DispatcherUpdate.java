package com.cowboysmall.noblox;

import com.cowboysmall.noblox.state.StateHandler;

public class DispatcherUpdate {

    private Handle handle;

    private int interested;
    private StateHandler stateHandler;


    //_________________________________________________________________________

    public DispatcherUpdate(Handle handle, int interested, StateHandler stateHandler) {

        this.handle = handle;
        this.interested = interested;
        this.stateHandler = stateHandler;
    }


    //_________________________________________________________________________

    public void execute() {

        handle.setAttachment(stateHandler);
        handle.setInterested(interested);
    }
}
