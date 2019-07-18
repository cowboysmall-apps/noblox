package com.cowboysmall.noblox;

import com.cowboysmall.noblox.dispatcher.Dispatcher;
import com.cowboysmall.noblox.memory.BufferPool;
import com.cowboysmall.noblox.request.RequestHandler;
import com.cowboysmall.noblox.state.AcceptHandler;
import com.cowboysmall.noblox.state.StateHandler;
import com.cowboysmall.noblox.thread.Executor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class Server implements Runnable {

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;
    private Executor executor;
    private BufferPool bufferPool;

    private boolean running = false;


    //_________________________________________________________________________

    public Server(String address, int port) {

        try {

            dispatcher = new Dispatcher();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(address, port));

            SelectionKey selectionKey = dispatcher.registerInterest(serverSocketChannel, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(selectionKey, this));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        try {

            while (running) {

                Set<SelectionKey> keys = dispatcher.getSelectedKeys();
                for (SelectionKey key : keys)
                    if (key.isValid())
                        ((StateHandler) key.attachment()).handleState();
                keys.clear();
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    public Dispatcher getDispatcher() {

        return dispatcher;
    }

    public RequestHandler getRequestHandler() {

        return requestHandler;
    }

    public Executor getExecutor() {

        return executor;
    }

    public BufferPool getBufferPool() {

        return bufferPool;
    }


    //_________________________________________________________________________

    public Server withRequestHandler(RequestHandler requestHandler) {

        this.requestHandler = requestHandler;
        return this;
    }

    public Server withThreadExecutor(Executor executor) {

        this.executor = executor;
        return this;
    }

    public Server withBufferPool(BufferPool bufferPool) {

        this.bufferPool = bufferPool;
        return this;
    }


    //_________________________________________________________________________

    public Server start() {

        running = true;
        new Thread(this).start();
        return this;
    }

    public Server stop() {

        running = false;
        return this;
    }
}
