package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.HandleException;

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
