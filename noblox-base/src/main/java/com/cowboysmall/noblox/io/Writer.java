package com.cowboysmall.noblox.io;

import com.cowboysmall.noblox.Channel;


public interface Writer {

    void writeTo(byte[] bytes, Channel channel);
}
