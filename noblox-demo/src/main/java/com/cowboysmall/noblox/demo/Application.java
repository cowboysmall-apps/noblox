package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.ReactorFactory;
import com.cowboysmall.noblox.ServerBuilder;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.nio.NIOAcceptor;
import com.cowboysmall.noblox.nio.NIOReactor;
import com.cowboysmall.noblox.thread.AllAvailableCoresExecutor;


public class Application {

    public static void main(String... args) throws Exception {

        new ServerBuilder()
                .withServerContext(
                        new ServerContext()
                                .withAcceptor(new NIOAcceptor("localhost", 8080))
                                .withReactorFactory(new ReactorFactory(NIOReactor.class))
                                .withExecutor(new AllAvailableCoresExecutor())
                                .withRequestHandler(new HttpEchoHandler())
                )
                .build()
                .start();
    }
}
