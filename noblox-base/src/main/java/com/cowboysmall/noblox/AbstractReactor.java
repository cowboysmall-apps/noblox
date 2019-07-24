package com.cowboysmall.noblox;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class AbstractReactor implements Reactor {

    private final List<ReactorUpdate> reactorUpdates = new LinkedList<>();

    private final Lock lock = new ReentrantLock();

    private boolean running = false;


    //_________________________________________________________________________

    @Override
    public boolean isRunning() {

        return running;
    }

    @Override
    public Reactor start() {

        running = true;
        return this;
    }

    @Override
    public Reactor stop() {

        running = false;
        return this;
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        try {

            while (running)
                dispatch();

        } catch (Exception e) {

            throw new ReactorException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public final void dispatch() {

        executeReactorUpdates();

        checkLock();
        waitForSelected();
        handleSelected();
    }


    //_________________________________________________________________________

    @Override
    public void addReactorUpdate(ReactorUpdate reactorUpdate) {

        lock();
        try {

            reactorUpdates.add(reactorUpdate);

        } finally {

            unlock();
        }
    }

    private void executeReactorUpdates() {

        lock();
        try {

            reactorUpdates.forEach(ReactorUpdate::apply);
            reactorUpdates.clear();

        } finally {

            unlock();
        }
    }


    //_________________________________________________________________________

    @Override
    public void unregisterInterest(Handle handle) {

        lock();
        try {

            wakeup();
            handle.cancel();

        } finally {

            unlock();
        }
    }


    //_________________________________________________________________________

    @Override
    public void lock() {

        lock.lock();
    }

    @Override
    public void unlock() {

        lock.unlock();
    }

    @Override
    public void checkLock() {

        lock.lock();
        lock.unlock();
    }


    //_________________________________________________________________________

    protected abstract void waitForSelected();

    protected abstract void handleSelected();
}
