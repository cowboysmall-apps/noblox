package com.cowboysmall.noblox.memory;

import com.cowboysmall.noblox.dispatcher.Channel;

import java.io.IOException;


public interface OutputBuffer {

    void writeTo(Channel channel) throws IOException;

    void append(byte[] bytes);

    void append(String string);
}
