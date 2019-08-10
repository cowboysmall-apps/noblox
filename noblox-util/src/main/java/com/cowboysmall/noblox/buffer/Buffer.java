package com.cowboysmall.noblox.buffer;


public interface Buffer {

    byte[] getBytes();

    int getLength();

    void append(byte[] bytes);
}
