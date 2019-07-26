package com.cowboysmall.noblox.reactor.channel;


import com.cowboysmall.noblox.reactor.Reactor;
import com.cowboysmall.noblox.reactor.channel.Channel;

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
