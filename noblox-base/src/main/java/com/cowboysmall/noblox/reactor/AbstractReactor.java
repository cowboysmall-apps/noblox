package com.cowboysmall.noblox.reactor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractReactor implements Reactor {

    private final List<Invocation> invocations = new LinkedList<>();

    private final Lock lock = new ReentrantLock();

    private int activeConnections;
    private boolean running;


    //_________________________________________________________________________

    @Override
    public void run() {

        while (running)
            dispatch();
    }


    //_________________________________________________________________________

    @Override
    public final void dispatch() {

        try {

            executeInvocations();

            checkLock();
            activeConnections = waitForSelected();
            handleSelected();

        } catch (Exception e) {

            // do some logging...
            // System.out.println(e.getMessage());
        }
    }

    @Override
    public int activeConnections() {

        return activeConnections;
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

    protected abstract int waitForSelected();

    protected abstract void handleSelected();
}
