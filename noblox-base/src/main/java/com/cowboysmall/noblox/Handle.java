package com.cowboysmall.noblox;


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
