package com.cowboysmall.noblox;


public interface Reactor extends Runnable {

    void dispatch();

    void wakeup();

    Handle registerAcceptInterest(Acceptor acceptor);

    Handle registerReadInterest(Channel channel);

    Handle registerWriteInterest(Channel channel);

    void unregisterInterest(Handle handle);

    void addReactorUpdate(ReactorUpdate reactorUpdate);

    boolean isRunning();

    Reactor start();

    Reactor stop();

    void lock();

    void unlock();

    void checkLock();
}
