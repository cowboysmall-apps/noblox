package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.state.StateHandler;

import java.nio.channels.SelectionKey;

public class NIODispatcherUpdate implements DispatcherUpdate {

    private SelectionKey selectionKey;

    private int interestOps;
    private StateHandler stateHandler;


    //_________________________________________________________________________

    public NIODispatcherUpdate(SelectionKey selectionKey, int interestOps, StateHandler stateHandler) {

        this.selectionKey = selectionKey;
        this.interestOps = interestOps;
        this.stateHandler = stateHandler;
    }


    //_________________________________________________________________________

    @Override
    public void execute() {

        selectionKey.attach(stateHandler);
        selectionKey.interestOps(interestOps);
    }
}
