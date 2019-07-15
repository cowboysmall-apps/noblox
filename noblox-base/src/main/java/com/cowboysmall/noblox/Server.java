package com.cowboysmall.noblox;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Arrays.copyOf;

public class Server implements Runnable {

    private final ReadWriteLock dataGuard = new ReentrantReadWriteLock();
    private final ReadWriteLock updateGuard = new ReentrantReadWriteLock();

    private final ConcurrentMap<SelectionKey, List<ByteBuffer>> data = new ConcurrentHashMap<>();
    private final List<Update> updates = new LinkedList<>();

    private final ByteBuffer buffer = ByteBuffer.allocate(2048);

    private final Selector selector;

    private RequestHandler requestHandler;

    private boolean running = false;


    //_________________________________________________________________________

    public Server(String address, int port) {

        try {

            selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(address, port));

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        try {

            while (running) {

                applyUpdates();

                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
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

            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    private void handleRead(SelectionKey selectionKey) {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            buffer.clear();
            socketChannel.read(buffer);

            byte[] updateData =
                    requestHandler.handleRequest(
                            copyOf(buffer.array(), buffer.position())
                    );

            addData(selectionKey, updateData);
            addUpdate(new Update(selectionKey, SelectionKey.OP_WRITE));

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
            dataGuard.writeLock().unlock();

            socketChannel.close();
            selectionKey.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }


    //_________________________________________________________________________

    private void applyUpdates() {

        updateGuard.writeLock().lock();
        for (Update update : updates)
            update.doUpdate();
        updates.clear();
        updateGuard.writeLock().unlock();
    }


    //_________________________________________________________________________

    private void addUpdate(Update update) {

        updateGuard.writeLock().lock();
        updates.add(update);
        updateGuard.writeLock().unlock();
    }

    private void addData(SelectionKey selectionKey, byte[] updateData) {

        dataGuard.writeLock().lock();
        if (!data.containsKey(selectionKey))
            data.put(selectionKey, new LinkedList<>());

        this.data.get(selectionKey).add(ByteBuffer.wrap(updateData));
        dataGuard.writeLock().lock();
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
