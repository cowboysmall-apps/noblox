package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.state.StateHandler;

import java.nio.channels.SelectionKey;

public class DispatcherEvent {

    private SelectionKey selectionKey;

    private int interestOps;
    private StateHandler stateHandler;


    //_________________________________________________________________________

    public DispatcherEvent(SelectionKey selectionKey, int interestOps, StateHandler stateHandler) {

        this.selectionKey = selectionKey;
        this.interestOps = interestOps;
        this.stateHandler = stateHandler;
    }


    //_________________________________________________________________________

    public void execute() {

        selectionKey.attach(stateHandler);
        selectionKey.interestOps(interestOps);
    }


    //_________________________________________________________________________

    public SelectionKey getSelectionKey() {

        return selectionKey;
    }
}
