package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.channel.ChannelWriter;
import com.cowboysmall.noblox.request.RequestHandler;

public class SimpleEchoHandler implements RequestHandler {

    @Override
    public void handleRequest(ChannelWriter channelWriter, byte[] input) {

        channelWriter.write(input);
    }
}
