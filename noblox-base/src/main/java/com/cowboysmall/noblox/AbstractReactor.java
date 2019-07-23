package com.cowboysmall.noblox;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class AbstractReactor implements Reactor {

    private final List<ReactorUpdate> reactorUpdates = new LinkedList<>();

    private final Lock lock = new ReentrantLock();


    //_________________________________________________________________________

    @Override
    public void dispatch() {

        executeReactorUpdates();

        waitForSelected();
        handleSelected();
    }


    //_________________________________________________________________________

    @Override
    public void addReactorUpdate(ReactorUpdate reactorUpdate) {

        lock.lock();
        try {

            reactorUpdates.add(reactorUpdate);

        } finally {

            lock.unlock();
        }
    }

    private void executeReactorUpdates() {

        lock.lock();
        try {

            reactorUpdates.forEach(ReactorUpdate::apply);
            reactorUpdates.clear();

        } finally {

            lock.unlock();
        }
    }


    //_________________________________________________________________________

    protected abstract void waitForSelected();

    protected abstract void handleSelected();
}
