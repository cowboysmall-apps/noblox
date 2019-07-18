package com.cowboysmall.noblox;

public class Server implements Runnable {

    private ServerContext serverContext;

    private boolean running = false;


    //_________________________________________________________________________

    @Override
    public void run() {

        try {

            while (running)
                serverContext.getDispatcher().dispatch();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    //_________________________________________________________________________

    public ServerContext getServerContext() {

        return serverContext;
    }


    //_________________________________________________________________________

    public Server withServerContext(ServerContext serverContext) {

        this.serverContext = serverContext;
        return this;
    }


    //_________________________________________________________________________

    public Server start() {

        running = true;
        serverContext.getExecutor().execute(this);
        return this;
    }

    public Server stop() {

        running = false;
        return this;
    }
}
