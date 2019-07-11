package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;

public class Application {

    public static void main(String... args) throws Exception {

        Server server = new Server("localhost", 8080);
        server.setRequestHandler(new HttpEchoHandler());
        server.start();
    }
}
