package com.cowboysmall.noblox.reactor;

import com.cowboysmall.noblox.reactor.channel.Acceptor;
import com.cowboysmall.noblox.reactor.channel.Channel;
import com.cowboysmall.noblox.reactor.channel.Handle;


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
