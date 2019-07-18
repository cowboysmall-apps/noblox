package com.cowboysmall.noblox.io;

import java.nio.ByteBuffer;

public interface ChannelWriter {

    void write(ByteBuffer byteBuffer);

    void write(byte[] bytes);

    void write(String string);
}
