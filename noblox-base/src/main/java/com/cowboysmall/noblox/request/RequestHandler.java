package com.cowboysmall.noblox.request;

import com.cowboysmall.noblox.channel.ChannelWriter;

public interface RequestHandler {

    void handleRequest(ChannelWriter channelWriter, byte[] input);
}
