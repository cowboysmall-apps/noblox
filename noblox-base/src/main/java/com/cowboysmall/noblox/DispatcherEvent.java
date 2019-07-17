package com.cowboysmall.noblox;

import java.nio.channels.SelectionKey;

public class DispatcherEvent {

    private SelectionKey selectionKey;

    private int interestOps;


    //_________________________________________________________________________

    public DispatcherEvent(SelectionKey selectionKey, int interestOps) {

        this.selectionKey = selectionKey;
        this.interestOps = interestOps;
    }


    //_________________________________________________________________________

    public void execute() {

        selectionKey.interestOps(interestOps);
    }


    //_________________________________________________________________________

    public SelectionKey getSelectionKey() {

        return selectionKey;
    }
}
