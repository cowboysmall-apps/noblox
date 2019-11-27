package com.cowboysmall.noblox.demo;

import com.cowboysmall.noblox.Server;
import com.cowboysmall.noblox.ServerContext;
import com.cowboysmall.noblox.demo.handler.HttpEchoHandler;
import com.cowboysmall.noblox.nio.reactor.NIOReactorManager;
import com.cowboysmall.noblox.nio.reactor.channel.NIOAcceptor;
import com.cowboysmall.noblox.thread.ThreadPoolExecutor;


public class Application {

    public static void main(String... args) throws Exception {

        ServerContext serverContext =
                new ServerContext()
                        .withAcceptor(new NIOAcceptor("localhost", 8080))
                        .withReactorManager(new NIOReactorManager())
                        .withExecutor(new ThreadPoolExecutor())
                        .withRequestHandler(new HttpEchoHandler());

        Server server =
                new Server()
                        .withServerContext(serverContext)
                        .build();

        server.start();
    }
}
