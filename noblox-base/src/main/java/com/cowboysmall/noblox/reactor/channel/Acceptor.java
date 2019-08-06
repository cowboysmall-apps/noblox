package com.cowboysmall.noblox.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;


public interface Acceptor<T> {

    T getImplementation();

    Channel accept();

    Handle registerWith(Reactor reactor);
}
