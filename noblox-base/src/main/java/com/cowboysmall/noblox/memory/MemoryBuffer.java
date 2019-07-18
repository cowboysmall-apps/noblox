package com.cowboysmall.noblox.memory;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface MemoryBuffer {

    byte[] readFrom(SocketChannel socketChannel) throws IOException;
}
