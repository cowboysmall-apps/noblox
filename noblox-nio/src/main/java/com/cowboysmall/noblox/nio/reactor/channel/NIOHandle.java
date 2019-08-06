package com.cowboysmall.noblox.nio.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;
import com.cowboysmall.noblox.reactor.channel.HandleException;

import java.nio.channels.SelectionKey;


public class NIOHandle implements Handle<SelectionKey> {

    private final SelectionKey selectionKey;

    private final Channel channel;
    private final Reactor reactor;


    //_________________________________________________________________________

    public NIOHandle(SelectionKey selectionKey, Channel channel, Reactor reactor) {

        this.selectionKey = selectionKey;
        this.channel = channel;
        this.reactor = reactor;
    }


    //_________________________________________________________________________

    @Override
    public SelectionKey getImplementation() {

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
