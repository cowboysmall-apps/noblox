package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.RequestHandler;

public class SimpleEchoHandler implements RequestHandler {

    @Override
    public byte[] handleRequest(byte[] input) {

        return input;
    }
}
