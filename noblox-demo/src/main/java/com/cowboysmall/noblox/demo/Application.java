package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.ServerBuilder;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.nio.NIOAcceptor;
import com.cowboysmall.noblox.nio.NIOReactorFactory;
import com.cowboysmall.noblox.thread.ThreadPoolExecutor;


public class Application {

    public static void main(String... args) throws Exception {

        new ServerBuilder()
                .withServerContext(
                        new ServerContext()
                                .withAcceptor(new NIOAcceptor("localhost", 8080))
                                .withReactorFactory(new NIOReactorFactory())
                                .withExecutor(new ThreadPoolExecutor())
                                .withRequestHandler(new HttpEchoHandler())
                )
                .build()
                .start();
    }
}
