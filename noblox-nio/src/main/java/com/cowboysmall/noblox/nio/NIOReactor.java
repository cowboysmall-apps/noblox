package com.cowboysmall.noblox.nio;

import com.cowboysmall.noblox.AbstractReactor;
import com.cowboysmall.noblox.Acceptor;
import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.Handle;
import com.cowboysmall.noblox.ReactorException;
import com.cowboysmall.noblox.handler.Handler;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;


public class NIOReactor extends AbstractReactor {

    private final Selector selector;


    //_________________________________________________________________________

    public NIOReactor() {

        try {

            selector = Selector.open();

        } catch (Exception e) {

            throw new ReactorException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void wakeup() {

        selector.wakeup();
    }


    //_________________________________________________________________________

    @Override
    public Handle registerAcceptInterest(Acceptor channel) {

        return new NIOHandle(registerInterest((SelectableChannel) channel.getAcceptor(), SelectionKey.OP_ACCEPT));
    }

    @Override
    public Handle registerReadInterest(Channel channel) {

        return new NIOHandle(registerInterest((SelectableChannel) channel.getChannel(), SelectionKey.OP_READ));
    }

    @Override
    public Handle registerWriteInterest(Channel channel) {

        return new NIOHandle(registerInterest((SelectableChannel) channel.getChannel(), SelectionKey.OP_WRITE));
    }


    //_________________________________________________________________________

    private SelectionKey registerInterest(SelectableChannel selectableChannel, int ops) {

        try {

            selectableChannel.configureBlocking(false);
            return selectableChannel.register(selector, ops);

        } catch (Exception e) {

            throw new ReactorException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    protected void waitForSelected() {

        try {

            selector.select();

        } catch (IOException e) {

            throw new ReactorException(e);
        }
    }

    @Override
    protected void handleSelected() {

        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        selectionKeys.stream()
                .filter(SelectionKey::isValid)
                .map(selectionKey -> (Handler) selectionKey.attachment())
                .forEach(Handler::handle);

        selectionKeys.clear();
    }
}
