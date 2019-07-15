package com.cowboysmall.noblox;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Runtime.getRuntime;
import static java.util.Arrays.copyOf;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class Server implements Runnable {

    private final ReadWriteLock dataGuard = new ReentrantReadWriteLock();

    private final ConcurrentMap<SelectionKey, List<ByteBuffer>> data = new ConcurrentHashMap<>();

    private final ExecutorService executorService =
            newFixedThreadPool(getRuntime().availableProcessors() - 1);

    private final ByteBuffer buffer = ByteBuffer.allocate(2048);

    private Dispatcher dispatcher;
    private RequestHandler requestHandler;

    private boolean running = false;


    //_________________________________________________________________________

    public Server(String address, int port) {

        try {

            dispatcher = new Dispatcher();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(address, port));

            dispatcher.registerInterest(serverSocketChannel, SelectionKey.OP_ACCEPT);

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
                        handleValid(key);
                keys.clear();
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    public void setRequestHandler(RequestHandler requestHandler) {

        this.requestHandler = requestHandler;
    }


    //_________________________________________________________________________

    private void handleValid(SelectionKey selectionKey) {

        if (selectionKey.isAcceptable())
            handleAccept(selectionKey);
        else if (selectionKey.isReadable())
            handleRead(selectionKey);
        else if (selectionKey.isWritable())
            handleWrite(selectionKey);
    }

    private void handleAccept(SelectionKey selectionKey) {

        try {

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            dispatcher.registerInterest(socketChannel, SelectionKey.OP_READ);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    private void handleRead(SelectionKey selectionKey) {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.read(buffer);

            executorService.execute(
                    new RequestHandlerAdapter(
                            this,
                            dispatcher,
                            requestHandler,
                            selectionKey,
                            copyOf(buffer.array(), buffer.position())
                    )
            );

            buffer.clear();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    private void handleWrite(SelectionKey selectionKey) {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            dataGuard.writeLock().lock();

            for (ByteBuffer byteBuffer : data.get(selectionKey))
                socketChannel.write(byteBuffer);
            data.get(selectionKey).clear();

            dataGuard.writeLock().unlock();

            socketChannel.close();
            selectionKey.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    public void addData(SelectionKey selectionKey, byte[] updateData) {

        dataGuard.writeLock().lock();

        if (!data.containsKey(selectionKey))
            data.put(selectionKey, new LinkedList<>());
        data.get(selectionKey).add(ByteBuffer.wrap(updateData));

        dataGuard.writeLock().unlock();
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
