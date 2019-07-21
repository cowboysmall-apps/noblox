package com.cowboysmall.noblox.dispatcher;

import java.io.IOException;

public interface Dispatcher {

    void dispatch() throws IOException;

    void wakeup();

    Key registerAcceptInterest(Acceptor acceptor) throws IOException;

    Key registerReadInterest(Channel channel) throws IOException;

    Key registerWriteInterest(Channel channel) throws IOException;

    void addDispatcherUpdate(DispatcherUpdate DispatcherUpdate);
}
