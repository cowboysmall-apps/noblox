package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.Context;
import com.cowboysmall.noblox.RequestHandler;

public class SimpleEchoHandler implements RequestHandler {

    @Override
    public void handleRequest(Context context) {

        context.getOutputBuffer().append(context.getBytesRead());
    }
}
