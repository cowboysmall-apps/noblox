package com.cowboysmall.noblox;

public interface RequestHandler {

    void handleRequest(Channel channel, byte[] input);
}
