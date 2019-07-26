package com.cowboysmall.noblox.reactor.channel;


public interface Channel {

    Object getChannel();

    byte[] read();

    void write(byte[] bytes);

    boolean bufferFull();
}
