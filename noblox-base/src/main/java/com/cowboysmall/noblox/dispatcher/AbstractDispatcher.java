package com.cowboysmall.noblox.dispatcher;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractDispatcher implements Dispatcher {

    private final List<DispatcherUpdate> dispatcherUpdates = new LinkedList<>();

    private final Lock lock = new ReentrantLock();


    //_________________________________________________________________________

    @Override
    public void dispatch() throws IOException {

        executeDispatcherUpdates();

        waitForSelected();
        handleSelected();
    }


    //_________________________________________________________________________

    @Override
    public void addDispatcherUpdate(DispatcherUpdate DispatcherUpdate) {

        lock.lock();
        try {

            dispatcherUpdates.add(DispatcherUpdate);

        } finally {

            lock.unlock();
        }
    }

    private void executeDispatcherUpdates() {

        lock.lock();
        try {

            dispatcherUpdates.forEach(DispatcherUpdate::execute);
            dispatcherUpdates.clear();

        } finally {

            lock.unlock();
        }
    }


    //_________________________________________________________________________

    protected abstract void waitForSelected() throws IOException;

    protected abstract void handleSelected();
}
