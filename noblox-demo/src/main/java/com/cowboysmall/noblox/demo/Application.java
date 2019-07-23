package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.ServerBuilder;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.buffer.BasicBuffer;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.nio.NIOAcceptor;
import com.cowboysmall.noblox.nio.NIOReactor;
import com.cowboysmall.noblox.nio.io.BasicNIOWriter;
import com.cowboysmall.noblox.nio.io.DirectNIOReader;
import com.cowboysmall.noblox.thread.AllAvailableCoresExecutor;


public class Application {

    public static void main(String... args) throws Exception {

        new ServerBuilder()
                .withServerContext(
                        new ServerContext()
                                .withAcceptor(new NIOAcceptor("localhost", 8080))
                                .withDispatcher(new NIOReactor())
                                .withReader(new DirectNIOReader())
                                .withWriter(new BasicNIOWriter())
                                .withBufferClass(BasicBuffer.class)
                                .withExecutor(new AllAvailableCoresExecutor())
                                .withRequestHandler(new HttpEchoHandler())
                )
                .build()
                .start();
    }
}
