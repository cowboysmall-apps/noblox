package com.cowboysmall.noblox;


public interface Reactor extends Runnable {

    void dispatch();

    void wakeup();

    int activeConnections();


    Handle registerAcceptor(Acceptor acceptor);

    Handle registerChannel(Channel channel);


    void invokeNow(Invocation invocation);

    void invokeLater(Invocation invocation);


    boolean isRunning();

    Reactor start();

    Reactor stop();


    void lock();

    void unlock();

    void checkLock();
}
