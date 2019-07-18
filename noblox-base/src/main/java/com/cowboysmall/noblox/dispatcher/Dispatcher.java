package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.state.StateHandler;

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

    public void dispatch() throws IOException {

        executeDispatcherEvents();

        selector.select();
        handleState(selector.selectedKeys());
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

    public void addDispatcherEvent(DispatcherEvent dispatcherEvent) {

        lock.lock();
        try {

            dispatcherEvents.add(dispatcherEvent);

        } finally {

            lock.unlock();
        }
    }


    //_________________________________________________________________________

    private void executeDispatcherEvents() {

        lock.lock();
        try {

            dispatcherEvents.forEach(DispatcherEvent::execute);
            dispatcherEvents.clear();

        } finally {

            lock.unlock();
        }
    }

    private void handleState(Set<SelectionKey> selectionKeys) {

        selectionKeys.stream()
                .filter(SelectionKey::isValid)
                .map(Dispatcher::extractStateHandler)
                .forEach(StateHandler::handleState);

        selectionKeys.clear();
    }


    //_________________________________________________________________________

    private static StateHandler extractStateHandler(SelectionKey selectionKey) {

        return (StateHandler) selectionKey.attachment();
    }
}
