package com.cowboysmall.noblox;


public interface Channel {

    byte[] read();

    int bytesRead();

    void write(byte[] bytes);

    Object getChannel();
}
