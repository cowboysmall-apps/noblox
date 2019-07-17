package com.cowboysmall.noblox;

import java.nio.ByteBuffer;

public interface Channel {

    void write(ByteBuffer byteBuffer);

    void write(byte[] bytes);

    void write(String string);
}
