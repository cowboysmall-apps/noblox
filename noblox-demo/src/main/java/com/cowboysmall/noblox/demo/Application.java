package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.memory.DirectBufferPool;
import com.cowboysmall.noblox.thread.AllAvailableCoresExecutor;

public class Application {

    public static void main(String... args) throws Exception {

        new Server("localhost", 8080)
                .withRequestHandler(new HttpEchoHandler())
                .withThreadExecutor(new AllAvailableCoresExecutor())
                .withBufferPool(new DirectBufferPool())
                .start();
    }
}
