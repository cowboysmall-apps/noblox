package com.cowboysmall.noblox.reactor.channel;


import com.cowboysmall.noblox.reactor.channel.Channel;

public interface Acceptor {

    Object getAcceptor();

    Channel accept();
}
