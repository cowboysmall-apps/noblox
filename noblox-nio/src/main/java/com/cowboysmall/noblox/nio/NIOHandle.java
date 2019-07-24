package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.HandleException;
import com.cowboysmall.noblox.Reactor;

import java.nio.channels.SelectionKey;


public class NIOHandle implements Handle {

    private SelectionKey selectionKey;

    private Channel channel;
    private Reactor reactor;


    //_________________________________________________________________________

    public NIOHandle(SelectionKey selectionKey, Reactor reactor) {

        this.selectionKey = selectionKey;
        this.reactor = reactor;
    }

    public NIOHandle(SelectionKey selectionKey, Channel channel, Reactor reactor) {

        this.selectionKey = selectionKey;
        this.channel = channel;
        this.reactor = reactor;
    }


    //_________________________________________________________________________

    @Override
    public Object getHandle() {

        return selectionKey;
    }

    @Override
    public Reactor getReactor() {

        return reactor;
    }

    @Override
    public Channel getChannel() {

        return channel;
    }

    @Override
    public void setAttachment(Object object) {

        selectionKey.attach(object);
    }

    @Override
    public void setNoInterest() {

        selectionKey.interestOps(0);
    }

    @Override
    public void setReadInterest() {

        selectionKey.interestOps(SelectionKey.OP_READ);
    }

    @Override
    public void setWriteInterest() {

        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    @Override
    public void cancel() {

        try {

            selectionKey.channel().close();
            selectionKey.cancel();

        } catch (Exception e) {

            throw new HandleException(e);
        }
    }
}
