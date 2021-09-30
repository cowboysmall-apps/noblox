package com.cowboysmall.noblox.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;

public interface Acceptor {

    Object getImplementation();

    Channel accept();

    Handle registerWith(Reactor reactor);
}
