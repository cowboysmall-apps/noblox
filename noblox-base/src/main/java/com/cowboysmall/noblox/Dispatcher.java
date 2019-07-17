package com.cowboysmall.noblox;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dispatcher {

    private final List<DispatcherEvent> dispatcherEvents = new LinkedList<>();
    private final ReadWriteLock dispatcherEventGuard = new ReentrantReadWriteLock();

    private final Selector selector;


    //_________________________________________________________________________

    public Dispatcher() throws IOException {

        selector = Selector.open();
    }


    //_________________________________________________________________________

//    public void acceptConnections() {
//
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        serverSocketChannel.bind(new InetSocketAddress(address, port));
//
//        registerInterest(serverSocketChannel, SelectionKey.OP_ACCEPT);
//    }


    //_________________________________________________________________________

    public Set<SelectionKey> getSelectedKeys() throws IOException {

        dispatcherEventGuard.writeLock().lock();
        dispatcherEvents.forEach(DispatcherEvent::execute);
        dispatcherEvents.clear();
        dispatcherEventGuard.writeLock().unlock();

        selector.select();
        return selector.selectedKeys();
    }

//    public void candidates() throws IOException {
//
//        dispatcherEventGuard.writeLock().lock();
//        dispatcherEvents.forEach(DispatcherEvent::execute);
//        dispatcherEvents.clear();
//        dispatcherEventGuard.writeLock().unlock();
//
//        selector.select();
//
//        Set<SelectionKey> selectionKeys = selector.selectedKeys();
//        selectionKeys.stream()
//                .filter(SelectionKey::isValid)
//                .map()
//    }

    public void wakeup() {

        selector.wakeup();
    }

    //_________________________________________________________________________

    public SelectionKey registerInterest(SelectableChannel selectableChannel, int ops) throws IOException {

        selectableChannel.configureBlocking(false);
        return selectableChannel.register(selector, ops);
    }


    //_________________________________________________________________________

    public void invokeLater(DispatcherEvent dispatcherEvent) {

        dispatcherEventGuard.writeLock().lock();
        dispatcherEvents.add(dispatcherEvent);
        dispatcherEventGuard.writeLock().unlock();
    }
}
