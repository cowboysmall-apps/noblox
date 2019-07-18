package com.cowboysmall.noblox.dispatcher;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dispatcher {

    private final List<DispatcherEvent> dispatcherEvents = new LinkedList<>();
    private final Lock lock = new ReentrantLock();


    private final Selector selector;


    //_________________________________________________________________________

    public Dispatcher() throws IOException {

        selector = Selector.open();
    }


    //_________________________________________________________________________

    public Set<SelectionKey> getSelectedKeys() throws IOException {

        lock.lock();
        try {

            dispatcherEvents.forEach(DispatcherEvent::execute);
            dispatcherEvents.clear();

        } finally {

            lock.unlock();
        }


        selector.select();
        return selector.selectedKeys();
    }

    public void wakeup() {

        selector.wakeup();
    }


    //_________________________________________________________________________

    public SelectionKey registerInterest(SelectableChannel selectableChannel, int ops) throws IOException {

        selectableChannel.configureBlocking(false);
        return selectableChannel.register(selector, ops);
    }


    //_________________________________________________________________________

    public void invokeLater(DispatcherEvent dispatcherEvent) {

        lock.lock();
        try {

            dispatcherEvents.add(dispatcherEvent);

        } finally {

            lock.unlock();
        }
    }
}
