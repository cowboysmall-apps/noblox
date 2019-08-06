package com.cowboysmall.noblox.nio.reactor;

import com.cowboysmall.noblox.reactor.AbstractReactor;
import com.cowboysmall.noblox.reactor.ReactorException;
import com.cowboysmall.noblox.reactor.handler.Handler;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;


public class NIOReactor extends AbstractReactor<Selector> {

    private final Selector selector;


    //_________________________________________________________________________

    public NIOReactor(Selector selector) {

        this.selector = selector;
    }

    public NIOReactor() {

        try {

            selector = Selector.open();

        } catch (Exception e) {

            throw new ReactorException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public Selector getImplementation() {

        return selector;
    }

    @Override
    public void wakeup() {

        selector.wakeup();
    }


    //_________________________________________________________________________

    @Override
    protected int waitForSelected() {

        try {

            return selector.select();

        } catch (Exception e) {

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
