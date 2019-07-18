package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.ServerBuilder;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.dispatcher.Dispatcher;
import com.cowboysmall.noblox.memory.DirectMemoryBuffer;
import com.cowboysmall.noblox.thread.AllAvailableCoresExecutor;

public class Application {

    public static void main(String... args) throws Exception {

        new ServerBuilder()
                .withAddress("localhost")
                .withPort(8080)
                .withServerContext(
                        new ServerContext()
                                .withDispatcher(new Dispatcher())
                                .withRequestHandler(new HttpEchoHandler())
                                .withExecutor(new AllAvailableCoresExecutor())
                                .withMemoryBuffer(new DirectMemoryBuffer())
                )
                .build()
                .start();
    }
}
