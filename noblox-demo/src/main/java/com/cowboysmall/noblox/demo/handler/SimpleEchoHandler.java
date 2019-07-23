package com.cowboysmall.noblox.demo.handler;

import com.cowboysmall.noblox.RequestContext;
import com.cowboysmall.noblox.RequestHandler;


public class SimpleEchoHandler implements RequestHandler {

    @Override
    public void handleRequest(RequestContext requestContext) {

        requestContext.getOutput().append(requestContext.getInput().getBytes());
    }
}
