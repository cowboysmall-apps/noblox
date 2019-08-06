package com.cowboysmall.noblox.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;


public interface Channel<T> {

    T getImplementation();

    byte[] read();

    void write(byte[] bytes);

    boolean bufferFull();

    Handle registerWith(Reactor reactor);
}
