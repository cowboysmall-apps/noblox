package com.cowboysmall.noblox;

import java.io.IOException;

public interface Dispatcher {

    void dispatch() throws IOException;

    void wakeup();

    Handle registerAcceptInterest(Acceptor acceptor) throws IOException;

    Handle registerReadInterest(Channel channel) throws IOException;

    Handle registerWriteInterest(Channel channel) throws IOException;

    void addDispatcherUpdate(DispatcherUpdate DispatcherUpdate);
}
