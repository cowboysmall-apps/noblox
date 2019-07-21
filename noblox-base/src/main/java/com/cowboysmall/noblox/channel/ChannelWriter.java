package com.cowboysmall.noblox.channel;

public interface ChannelWriter {

    void write(byte[] bytes);

    void write(String string);
}
