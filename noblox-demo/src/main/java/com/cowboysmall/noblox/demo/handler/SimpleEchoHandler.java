package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.Channel;
import com.cowboysmall.noblox.RequestHandler;

public class SimpleEchoHandler implements RequestHandler {

    @Override
    public void handleRequest(Channel channel, byte[] input) {

        channel.write(input);
    }
}
