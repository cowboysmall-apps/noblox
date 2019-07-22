package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NIOHandle implements Handle {

    private SelectionKey selectionKey;


    //_________________________________________________________________________

    public NIOHandle(SelectionKey selectionKey) {

        this.selectionKey = selectionKey;
    }


    //_________________________________________________________________________

    @Override
    public Object getHandle() {

        return selectionKey;
    }

    @Override
    public Channel getChannel() {

        return new NIOChannel((SocketChannel) selectionKey.channel());
    }

    @Override
    public void setAttachment(Object object) {

        selectionKey.attach(object);
    }

    @Override
    public void setInterested(int ops) {

        selectionKey.interestOps(ops);
    }

    @Override
    public void cancel() throws IOException {

        selectionKey.channel().close();
        selectionKey.cancel();
    }
}
