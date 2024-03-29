package com.cowboysmall.noblox.reactor;

public interface Reactor extends Runnable {

    Object getImplementation();

    void dispatch();

    void wakeup();

    int activeConnections();


    void invokeNow(Invocation invocation);

    void invokeLater(Invocation invocation);


    boolean isRunning();

    Reactor start();

    Reactor stop();


    void lock();

    void unlock();

    void checkLock();
}
