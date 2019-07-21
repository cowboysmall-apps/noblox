package com.cowboysmall.noblox.dispatcher;

import com.cowboysmall.noblox.memory.InputBuffer;
import com.cowboysmall.noblox.memory.OutputBuffer;

import java.io.IOException;

public interface Channel {

    byte[] read(InputBuffer inputBuffer) throws IOException;

    void write(OutputBuffer outputBuffer) throws IOException;

    Object getChannel();

    void close() throws IOException;
}
