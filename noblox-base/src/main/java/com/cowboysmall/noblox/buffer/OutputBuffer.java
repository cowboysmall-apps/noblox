package com.cowboysmall.noblox.buffer;

import com.cowboysmall.noblox.Channel;

import java.io.IOException;


public interface OutputBuffer {

    void writeTo(Channel channel) throws IOException;

    void append(byte[] bytes);

    void append(String string);
}
