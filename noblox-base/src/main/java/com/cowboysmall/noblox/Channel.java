package com.cowboysmall.noblox;

public interface Channel {

    byte[] read();

    void write(byte[] bytes);

    Object getChannel();
}
