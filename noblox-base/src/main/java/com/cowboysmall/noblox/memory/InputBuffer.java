package com.cowboysmall.noblox.memory;

import com.cowboysmall.noblox.dispatcher.Channel;

import java.io.IOException;


public interface InputBuffer {

    byte[] readFrom(Channel channel) throws IOException;
}
