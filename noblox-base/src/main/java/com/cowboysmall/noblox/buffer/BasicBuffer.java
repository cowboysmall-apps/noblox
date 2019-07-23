package com.cowboysmall.noblox.buffer;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.arraycopy;


public class BasicBuffer implements Buffer {

    private List<byte[]> buffer = new LinkedList<>();


    //_________________________________________________________________________

    @Override
    public byte[] getBytes() {

        int total =
                buffer.stream()
                        .mapToInt(b -> b.length)
                        .sum();

        byte[] bytes = new byte[total];

        int current = 0;
        for (byte[] b : buffer) {

            arraycopy(b, 0, bytes, current, bytes.length);
            current += bytes.length;
        }

        return bytes;
    }

    @Override
    public void append(byte[] bytes) {

        this.buffer.add(bytes);
    }
}
