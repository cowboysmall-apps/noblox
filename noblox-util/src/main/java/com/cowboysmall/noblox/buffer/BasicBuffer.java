package com.cowboysmall.noblox.buffer;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.arraycopy;


public class BasicBuffer implements Buffer {

    private List<byte[]> buffer = new LinkedList<>();

    private int length;


    //_________________________________________________________________________

    @Override
    public byte[] getBytes() {

        byte[] bytes = new byte[length];

        int current = 0;
        for (byte[] b : buffer) {

            arraycopy(b, 0, bytes, current, b.length);
            current += b.length;
        }

        return bytes;
    }

    @Override
    public int getLength() {

        return length;
    }

    @Override
    public void append(byte[] bytes) {

        if (bytes == null)
            throw new IllegalArgumentException("bytes cannot be null");

        length += bytes.length;
        buffer.add(bytes);
    }
}
