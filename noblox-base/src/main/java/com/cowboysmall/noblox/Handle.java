package com.cowboysmall.noblox;

public interface Handle {

    Object getHandle();

    Channel getChannel();

    void setAttachment(Object object);

    void setNoInterest();

    void setReadInterest();

    void setWriteInterest();

    void cancel();
}
