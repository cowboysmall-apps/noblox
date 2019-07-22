package com.cowboysmall.noblox.buffer;

import com.cowboysmall.noblox.Channel;

import java.io.IOException;


public interface InputBuffer {

    byte[] readFrom(Channel channel) throws IOException;
}
