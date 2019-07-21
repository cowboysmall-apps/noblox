package com.cowboysmall.noblox.dispatcher;

public interface Key {

    Object getKey();

    Channel getChannel();

    Object getAttachment();

    void attach(Object object);

    void interested(int ops);

    void cancel();
}
