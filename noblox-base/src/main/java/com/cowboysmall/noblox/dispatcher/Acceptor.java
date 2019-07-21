package com.cowboysmall.noblox.dispatcher;

import java.io.IOException;

public interface Acceptor {

    Object getAcceptor();

    Channel accept() throws IOException;
}
