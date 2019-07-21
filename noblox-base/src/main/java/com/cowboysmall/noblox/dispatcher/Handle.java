package com.cowboysmall.noblox.dispatcher;

import java.io.IOException;

public interface Handle {

    Object getKey();

    Object getChannel();

    void cancel() throws IOException;
}
