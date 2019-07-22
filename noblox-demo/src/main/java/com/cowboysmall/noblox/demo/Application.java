package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.ServerBuilder;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.nio.NIOAcceptor;
import com.cowboysmall.noblox.nio.NIODispatcher;
import com.cowboysmall.noblox.nio.buffer.BasicNIOOutputBuffer;
import com.cowboysmall.noblox.nio.buffer.DirectNIOInputBuffer;
import com.cowboysmall.noblox.thread.AllAvailableCoresExecutor;

public class Application {

    public static void main(String... args) throws Exception {

        new ServerBuilder()
                .withAcceptor(new NIOAcceptor("localhost", 8080))
                .withServerContext(
                        new ServerContext()
                                .withDispatcher(new NIODispatcher())
                                .withInputBuffer(new DirectNIOInputBuffer())
                                .withOutputBufferClass(BasicNIOOutputBuffer.class)
                                .withExecutor(new AllAvailableCoresExecutor())
                                .withRequestHandler(new HttpEchoHandler())
                )
                .build()
                .start();
    }
}
