package com.cowboysmall.noblox;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.copyOf;

public class ReadWriteManager implements ChannelManager, Channel, Runnable {

    private final ByteBuffer input = ByteBuffer.allocate(2048);
    private final List<ByteBuffer> output = new LinkedList<>();

//    private boolean readComplete;
//    private boolean writeComplete;

    private SelectionKey selectionKey;
    private Server server;


    //_________________________________________________________________________

    public ReadWriteManager(SelectionKey selectionKey, Server server) {

        this.selectionKey = selectionKey;
        this.server = server;
    }


    //_________________________________________________________________________

    @Override
    public void execute() {

        if (selectionKey.isReadable())
            readFrom();
        else if (selectionKey.isWritable())
            writeTo();
    }


    //_________________________________________________________________________

    @Override
    public void run() {

        server.getRequestHandler().handleRequest(this, copyOf(input.array(), input.position()));

        server.getDispatcher().invokeLater(new DispatcherEvent(selectionKey, SelectionKey.OP_WRITE));
        server.getDispatcher().wakeup();
    }


    //_________________________________________________________________________

    public void write(ByteBuffer byteBuffer) {

        output.add(byteBuffer);
    }

    @Override
    public void write(byte[] bytes) {

        write(ByteBuffer.wrap(bytes));
    }

    @Override
    public void write(String string) {

        write(string.getBytes());
    }

    //_________________________________________________________________________

//    public boolean isReadComplete() {
//
//        return readComplete;
//    }
//
//    public boolean isWriteComplete() {
//
//        return writeComplete;
//    }


    //_________________________________________________________________________

    public void readFrom() {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.read(input);

//            readComplete = true;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void writeTo() {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            for (ByteBuffer byteBuffer : output)
                socketChannel.write(byteBuffer);

            socketChannel.close();
            selectionKey.cancel();

//            writeComplete = true;

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
