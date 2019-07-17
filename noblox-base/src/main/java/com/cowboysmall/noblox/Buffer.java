package com.cowboysmall.noblox;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface Buffer {
    void readFrom(SocketChannel socketChannel) throws IOException;

    void writeTo(SocketChannel socketChannel) throws IOException;

    byte[] bytesRead();
}
