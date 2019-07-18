package com.cowboysmall.noblox.io;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface ChannelBuffer {

    void readFrom(SocketChannel socketChannel) throws IOException;

    void writeTo(SocketChannel socketChannel) throws IOException;

    byte[] bytesRead();
}
