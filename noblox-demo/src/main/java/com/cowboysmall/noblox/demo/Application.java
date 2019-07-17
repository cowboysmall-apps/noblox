package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;

public class Application {

    public static void main(String... args) throws Exception {

        new Server("localhost", 8080)
                .withRequestHandler(new HttpEchoHandler())
                .start();
    }
}
