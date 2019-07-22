package com.cowboysmall.noblox;

import java.io.IOException;

public interface Handle {

    Object getHandle();

    Channel getChannel();

    void setAttachment(Object object);

    void setInterested(int ops);

    void cancel() throws IOException;
}
