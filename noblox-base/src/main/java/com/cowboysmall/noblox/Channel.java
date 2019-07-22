package com.cowboysmall.noblox;

import com.cowboysmall.noblox.buffer.InputBuffer;
import com.cowboysmall.noblox.buffer.OutputBuffer;

import java.io.IOException;

public interface Channel {

    byte[] read(InputBuffer inputBuffer) throws IOException;

    void write(OutputBuffer outputBuffer) throws IOException;

    Object getChannel();
}
