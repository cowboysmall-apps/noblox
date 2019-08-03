package com.cowboysmall.noblox.reactor.channel;

import com.cowboysmall.noblox.reactor.Reactor;


public interface Handle {

    Object getHandle();

    Reactor getReactor();

    Channel getChannel();


    void setAttachment(Object object);


    void setNoInterest();

    void setReadInterest();

    void setWriteInterest();


    void cancel();
}
