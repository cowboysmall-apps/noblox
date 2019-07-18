package com.cowboysmall.noblox.memory;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface BufferPool {

    byte[] readFrom(SocketChannel socketChannel) throws IOException;
}
