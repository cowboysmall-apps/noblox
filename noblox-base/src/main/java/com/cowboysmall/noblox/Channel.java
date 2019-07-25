package com.cowboysmall.noblox;


public interface Channel {

    Object getChannel();

    byte[] read();

    void write(byte[] bytes);

    boolean bufferFull();
}
