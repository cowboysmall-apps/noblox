package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.state.StateHandler;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class NIODispatcher extends AbstractDispatcher {

    private final Selector selector;


    //_________________________________________________________________________

    public NIODispatcher() throws IOException {

        selector = Selector.open();
    }


    //_________________________________________________________________________

    @Override
    public void wakeup() {

        selector.wakeup();
    }

    @Override
    public Key registerAcceptInterest(Acceptor channel) throws IOException {

        return new NIOKey(registerInterest((SelectableChannel) channel.getAcceptor(), SelectionKey.OP_ACCEPT));
    }

    @Override
    public Key registerReadInterest(Channel channel) throws IOException {

        return new NIOKey(registerInterest((SelectableChannel) channel.getChannel(), SelectionKey.OP_READ));
    }

    @Override
    public Key registerWriteInterest(Channel channel) throws IOException {

        return new NIOKey(registerInterest((SelectableChannel) channel.getChannel(), SelectionKey.OP_WRITE));
    }


    //_________________________________________________________________________

    @Override
    protected void waitForSelected() throws IOException {

        selector.select();
    }

    @Override
    protected void handleSelected() {

        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        selectionKeys.stream()
                .filter(SelectionKey::isValid)
                .map(selectionKey -> (StateHandler) selectionKey.attachment())
                .forEach(StateHandler::handleState);

        selectionKeys.clear();
    }


    //_________________________________________________________________________

    private SelectionKey registerInterest(SelectableChannel selectableChannel, int ops) throws IOException {

        selectableChannel.configureBlocking(false);
        return selectableChannel.register(selector, ops);
    }
}
