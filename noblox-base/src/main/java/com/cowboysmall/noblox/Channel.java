package com.cowboysmall.noblox;


public interface Channel {

    byte[] read();

    boolean bufferFull();

    void write(byte[] bytes);

    Object getChannel();
}
