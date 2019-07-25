package com.cowboysmall.noblox;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class AbstractReactor implements Reactor {

    private final List<Invocation> invocations = new LinkedList<>();

    private final Lock lock = new ReentrantLock();

    private boolean running;


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

        executeInvocations();

        checkLock();
        waitForSelected();
        handleSelected();
    }


    //_________________________________________________________________________

    @Override
    public void invokeNow(Invocation invocation) {

        lock();
        try {

            wakeup();
            invocation.invoke(this);

        } finally {

            unlock();
        }
    }

    @Override
    public void invokeLater(Invocation invocation) {

        invocations.add(invocation);
    }


    //_________________________________________________________________________

    private void executeInvocations() {

        invocations.forEach(invocation -> invocation.invoke(this));
        invocations.clear();
    }


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
