package com.cowboysmall.noblox;


public interface Reactor {

    void dispatch();

    void wakeup();

    Handle registerAcceptInterest(Acceptor acceptor);

    Handle registerReadInterest(Channel channel);

    Handle registerWriteInterest(Channel channel);

    void addReactorUpdate(ReactorUpdate reactorUpdate);
}
