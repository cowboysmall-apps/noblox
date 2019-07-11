package com.cowboysmall.noblox;

import java.nio.channels.SelectionKey;

public class Update {

    private SelectionKey selectionKey;

    private int interestOps;


    //_________________________________________________________________________

    public Update(SelectionKey selectionKey, int interestOps) {

        this.selectionKey = selectionKey;
        this.interestOps = interestOps;
    }


    //_________________________________________________________________________

    public void doUpdate() {

        selectionKey.interestOps(interestOps);
    }


    //_________________________________________________________________________

    public SelectionKey getSelectionKey() {

        return selectionKey;
    }
}
