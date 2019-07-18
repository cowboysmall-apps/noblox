package com.cowboysmall.noblox.state;

import com.cowboysmall.noblox.io.ChannelContext;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class WriteHandler implements StateHandler {

    private SelectionKey selectionKey;
    private ChannelContext channelContext;


    //_________________________________________________________________________

    public WriteHandler(SelectionKey selectionKey, ChannelContext channelContext) {

        this.selectionKey = selectionKey;
        this.channelContext = channelContext;
    }


    //_________________________________________________________________________

    @Override
    public void handleState() {

        try {

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            channelContext.writeTo(socketChannel);

            socketChannel.close();
            selectionKey.cancel();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
