package com.cowboysmall.noblox.channel;

import com.cowboysmall.noblox.dispatcher.Channel;

import java.io.IOException;

public interface ChannelBuffer {

    void readFrom(Channel channel) throws IOException;

    void writeTo(Channel channel) throws IOException;

    byte[] bytesRead();
}
