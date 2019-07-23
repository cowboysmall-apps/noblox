package com.cowboysmall.noblox;

import com.cowboysmall.noblox.io.Reader;
import com.cowboysmall.noblox.io.Writer;


public interface Channel {

    byte[] read(Reader reader);

    void write(byte[] bytes, Writer writer);

    Object getChannel();
}
