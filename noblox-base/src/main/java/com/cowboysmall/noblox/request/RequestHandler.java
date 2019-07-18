package com.cowboysmall.noblox.request;

import com.cowboysmall.noblox.io.ChannelWriter;

public interface RequestHandler {

    void handleRequest(ChannelWriter channelWriter, byte[] input);
}
