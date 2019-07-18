package com.cowboysmall.noblox.channel;

import java.nio.ByteBuffer;

public interface ChannelWriter {

    void write(ByteBuffer byteBuffer);

    void write(byte[] bytes);

    void write(String string);
}
