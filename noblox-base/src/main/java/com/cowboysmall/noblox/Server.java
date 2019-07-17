package com.cowboysmall.noblox;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class Server implements Runnable {

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;

    private boolean running = false;


    //_________________________________________________________________________

    public Server(String address, int port) {

        try {

            dispatcher = new Dispatcher();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(address, port));

            SelectionKey selectionKey = dispatcher.registerInterest(serverSocketChannel, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptManager(selectionKey, this));

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
                        ((ChannelManager) key.attachment()).execute();
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


    //_________________________________________________________________________

    public Server withRequestHandler(RequestHandler requestHandler) {

        this.requestHandler = requestHandler;
        return this;
    }

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
