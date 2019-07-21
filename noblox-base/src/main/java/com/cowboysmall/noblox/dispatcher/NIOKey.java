package com.cowboysmall.noblox.dispatcher;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NIOKey implements Key {

    private SelectionKey selectionKey;


    //_________________________________________________________________________

    public NIOKey(SelectionKey selectionKey) {

        this.selectionKey = selectionKey;
    }


    //_________________________________________________________________________

    @Override
    public Object getKey() {

        return selectionKey;
    }

    @Override
    public Channel getChannel() {

        return new NIOChannel((SocketChannel) selectionKey.channel());
    }

    @Override
    public Object getAttachment() {

        return selectionKey.attachment();
    }

    @Override
    public void attach(Object object) {

        selectionKey.attach(object);
    }

    @Override
    public void interested(int ops) {

        selectionKey.interestOps(ops);
    }

    @Override
    public void cancel() {

        selectionKey.cancel();
    }
}
